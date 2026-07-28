package net.fedoo0.mysteries.beyonder.pathways.main.fool;
import net.fedoo0.mysteries.MysteriesMod;
import net.fedoo0.mysteries.item.custom.Dagger;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;


public class Ingredients {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MysteriesMod.MOD_ID);


    // Sequence 9
    public  static final DeferredItem<Item> LAVOS_SQUID_BLOOD = ITEMS.register("lavos_squid_blood",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public  static final DeferredItem<Item> STAR_CRYSTAL = ITEMS.register("star_crystal",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public  static final DeferredItem<Item> PURIFIED_WATER = ITEMS.register("purified_water",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON)));

    public  static final DeferredItem<Item> NIGHT_VANILLA_LIQUIDS = ITEMS.register("night_vanilla_liquids",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON)));

    public  static final DeferredItem<Item> POSION_HEMLOCK = ITEMS.register("posion_hemlock",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON)));

    public  static final DeferredItem<Item> DRAGON_BLOOD_GRASS_POWDER = ITEMS.register("dragon_blood_grass_powder",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON)));

    // Sequence 8
    public static final DeferredItem<Item> HORNACIS_GOAT_HORN = ITEMS.register("hornacis_goat_horn",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> HUMAN_FACED_ROSE_STALK = ITEMS.register("human_faced_rose_stalk",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> TORNAPPLE_JUICE = ITEMS.register("tornapple_juice",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> BLACK_RIMMED_SUNFLOWER_POWDER = ITEMS.register("black_rimmed_sunflower_powder",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> GOLDEN_CLOAK_GRASS_POWDER = ITEMS.register("golden_cloak_grass_powder",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    // Sequence 7
    public static final DeferredItem<Item> MIST_TREANT_TRUE_ROOT = ITEMS.register("mist_treant_true_root",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> BLACK_PANTHER_SPINAL_FLUID = ITEMS.register("black_panther_spinal_fluid",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> MIST_TREANT_JUICE = ITEMS.register("mist_treant_juice",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> DROPLET_GEM_POWDER = ITEMS.register("droplet_gem_powder",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> FANTASY_GRASS_ESSENTIAL_OIL = ITEMS.register("fantasy_grass_essential_oil",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    // Sequence 6
    public static final DeferredItem<Item> THOUSAND_FACED_HUNTER_PITUITARY = ITEMS.register("thousand_faced_hunter_pituitary",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> HUMAN_SKINNED_SHADOW_CHARACTERISTIC = ITEMS.register("human_skinned_shadow_characteristic",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> THOUSAND_FACED_HUNTER_BLOOD = ITEMS.register("thousand_faced_hunter_blood",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> BLACK_JIMSONWEED_JUICE = ITEMS.register("black_jimsonweed_juice",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> DRAGON_TOOTH_GRASS_POWDER = ITEMS.register("dragon_tooth_grass_powder",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> DEEP_SEA_NAGA_HAIR = ITEMS.register("deep_sea_naga_hair",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    // Sequence 5
    public static final DeferredItem<Item> ANCIENT_WRAITH_DUST = ITEMS.register("ancient_wraith_dust",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> SIX_WINGED_GARGOYLE_CORE = ITEMS.register("six_winged_gargoyle_core",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> GOLDEN_SPRING_WATER = ITEMS.register("golden_spring_water",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> DRAGO_BARK = ITEMS.register("drago_bark",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> ANCIENT_WRAITH_REMNANT_SPIRITUALITY = ITEMS.register("ancient_wraith_remnant_spirituality",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SIX_WINGED_GARGOYLE_EYES = ITEMS.register("six_winged_gargoyle_eyes",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    // Sequence 4
    public static final DeferredItem<Item> BIZARRO_BANE_MAIN_EYE = ITEMS.register("bizarro_bane_main_eye",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> SPIRIT_WORLD_PLUNDERER_SOUL = ITEMS.register("spirit_world_plunderer_soul",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> BIZARRO_BANE_BLOOD = ITEMS.register("bizarro_bane_blood",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> SPIRIT_WORLD_PLUNDERER_DUST = ITEMS.register("spirit_world_plunderer_dust",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> RED_HAIR_BIRCH_BARK = ITEMS.register("red_hair_birch_bark",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> GOLDEN_GRAPEVINES = ITEMS.register("golden_grapevines",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));
    public static final DeferredItem<Item> RUBBER_MASK = ITEMS.register("rubber_mask",
            () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
