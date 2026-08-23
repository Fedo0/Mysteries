package net.fedoo0.mysteries;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fedoo0.mysteries.beyonder.Beyonder;
import net.fedoo0.mysteries.beyonder.BeyonderData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Map;
import java.util.UUID;



public class DebugCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("beyonderdebug")
                        .requires(source -> source.hasPermission(2))

                        // /beyonderdebug set <player> <pathway> <sequence>
                        .then(Commands.literal("set")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .then(Commands.argument("pathway", StringArgumentType.word())
                                                .then(Commands.argument("sequence", IntegerArgumentType.integer(0, 9))
                                                        .executes(DebugCommands::setBeyonder)))))

                        // /beyonderdebug get <player>
                        .then(Commands.literal("get")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(DebugCommands::getBeyonder)))

                        // /beyonderdebug remove <player>
                        .then(Commands.literal("remove")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(DebugCommands::removeBeyonder)))

                        // /beyonderdebug list
                        .then(Commands.literal("list")
                                .executes(DebugCommands::listBeyonders))

                        // /beyonderdebug setsequence <player> <sequence>
                        .then(Commands.literal("setsequence")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .then(Commands.argument("sequence", IntegerArgumentType.integer(0, 9))
                                                .executes(DebugCommands::setSequence))))

                        // /beyonderdebug spirituality <player> <amount>  (delta, can be negative)
                        .then(Commands.literal("spirituality")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                                .executes(DebugCommands::modifySpirituality))))

                        // /beyonderdebug digestion <player> <amount>  (0.0-1.0, additive)
                        .then(Commands.literal("digestion")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg())
                                                .executes(DebugCommands::progressDigestion))))

                        // /beyonderdebug madness <player> <amount>  (delta, can be negative)
                        .then(Commands.literal("madness")
                                .then(Commands.argument("target", EntityArgument.player())
                                        .then(Commands.argument("amount", DoubleArgumentType.doubleArg())
                                                .executes(DebugCommands::modifyMadness))))
        );
    }

    private static int setBeyonder(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
        String pathway = StringArgumentType.getString(ctx, "pathway");
        int sequence = IntegerArgumentType.getInteger(ctx, "sequence");

        Beyonder.get(ctx.getSource().getServer().overworld()).registerBeyonder(target.getUUID(), pathway, sequence, ctx.getSource().getServer().overworld());

        ctx.getSource().sendSuccess(() -> Component.literal(
                "Set " + target.getName().getString() + " as Beyonder: pathway=" + pathway + ", sequence=" + sequence
        ), true);
        return 1;
    }

    private static int getBeyonder(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
        UUID uuid = target.getUUID();

        if (!Beyonder.get(ctx.getSource().getServer().overworld()).isBeyonder(uuid)) {
            ctx.getSource().sendFailure(Component.literal(
                    target.getName().getString() + " is not a Beyonder."
            ));
            return 0;
        }

        BeyonderData data = Beyonder.get(ctx.getSource().getServer().overworld()).getBeyonder(uuid);
        ctx.getSource().sendSuccess(() -> Component.literal(
                target.getName().getString() + " -> pathway=" + data.getPathway()
                        + ", sequence=" + data.getSequence()
                        + ", spirituality=" + data.getSpirituality()
                        + ", digestion=" + data.getDigestion()
                        + ", madness=" + data.getMadness()
        ), false);
        return 1;
    }

    private static int removeBeyonder(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(ctx, "target");

        if (!Beyonder.get(ctx.getSource().getServer().overworld()).isBeyonder(target.getUUID())) {
            ctx.getSource().sendFailure(Component.literal(
                    target.getName().getString() + " is not registered as a Beyonder."
            ));
            return 0;
        }

        Beyonder.get(ctx.getSource().getServer().overworld()).removeBeyonder(target.getUUID(), ctx.getSource().getServer().overworld());
        ctx.getSource().sendSuccess(() -> Component.literal(
                "Removed Beyonder status from " + target.getName().getString()
        ), true);
        return 1;
    }

    private static BeyonderData requireBeyonder(CommandContext<CommandSourceStack> ctx, ServerPlayer target) {
        BeyonderData data = Beyonder.get(ctx.getSource().getServer().overworld()).getBeyonder(target.getUUID());
        if (data == null) {
            ctx.getSource().sendFailure(Component.literal(
                    target.getName().getString() + " is not a Beyonder."
            ));
        }
        return data;
    }

    private static int setSequence(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
        BeyonderData data = requireBeyonder(ctx, target);
        if (data == null) return 0;

        int sequence = IntegerArgumentType.getInteger(ctx, "sequence");
        data.setSequence(sequence);
        Beyonder.get(ctx.getSource().getServer().overworld()).setDirty();
        ctx.getSource().sendSuccess(() -> Component.literal(
                "Set " + target.getName().getString() + "'s sequence to " + sequence
        ), true);
        return 1;
    }

    private static int modifySpirituality(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
        BeyonderData data = requireBeyonder(ctx, target);
        if (data == null) return 0;

        int amount = IntegerArgumentType.getInteger(ctx, "amount");
        data.modifySpirituality(amount);
        Beyonder.get(ctx.getSource().getServer().overworld()).setDirty();
        ctx.getSource().sendSuccess(() -> Component.literal(
                "Modified " + target.getName().getString() + "'s spirituality by " + amount
                        + " (now " + data.getSpirituality() + ")"
        ), true);
        return 1;
    }

    private static int progressDigestion(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
        BeyonderData data = requireBeyonder(ctx, target);
        if (data == null) return 0;

        double amount = DoubleArgumentType.getDouble(ctx, "amount");
        data.progressDigestion(amount);
        Beyonder.get(ctx.getSource().getServer().overworld()).setDirty();
        ctx.getSource().sendSuccess(() -> Component.literal(
                "Progressed " + target.getName().getString() + "'s digestion by " + amount
                        + " (now " + data.getDigestion() + (data.isFullyDigested() ? ", FULLY DIGESTED" : "") + ")"
        ), true);
        return 1;
    }

    private static int modifyMadness(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer target = EntityArgument.getPlayer(ctx, "target");
        BeyonderData data = requireBeyonder(ctx, target);
        if (data == null) return 0;

        double amount = DoubleArgumentType.getDouble(ctx, "amount");
        data.modifyMadness(amount);
        Beyonder.get(ctx.getSource().getServer().overworld()).setDirty();
        ctx.getSource().sendSuccess(() -> Component.literal(
                "Modified " + target.getName().getString() + "'s madness by " + amount
                        + " (now " + data.getMadness() + (data.isMad() ? ", MAD" : "") + ")"
        ), true);
        return 1;
    }

    private static int listBeyonders(CommandContext<CommandSourceStack> ctx) {
        Map<UUID, BeyonderData> registry = Beyonder.get(ctx.getSource().getLevel()).getBeyonderRegistry();

        if (registry.isEmpty()) {
            ctx.getSource().sendSuccess(() -> Component.literal("No Beyonders currently registered."), false);
            return 0;
        }

        StringBuilder sb = new StringBuilder("Registered Beyonders (" + registry.size() + "):");
        registry.forEach((uuid, data) ->
                sb.append("\n- ").append(uuid).append(": pathway=").append(data.getPathway())
                        .append(", sequence=").append(data.getSequence())
                        .append(", spirituality=").append(data.getSpirituality())
                        .append(", digestion=").append(data.getDigestion())
                        .append(", madness=").append(data.getMadness())
        );

        ctx.getSource().sendSuccess(() -> Component.literal(sb.toString()), false);
        return registry.size();
    }
}