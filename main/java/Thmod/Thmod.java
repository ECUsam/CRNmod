package Thmod;

import Thmod.cards.*;
import Thmod.cards.Curses.*;
import Thmod.cards.Derivations.*;
import Thmod.events.SZSIYUDI;
import Thmod.monsters.DIYUSI_m;
import Thmod.monsters.kawai_m;
import Thmod.posions.BeerBeer;
import Thmod.relics.*;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import Thmod.characters.CRN;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

import static Thmod.pathes.AbstractCardEnum.CRN_COLOR;
import static Thmod.pathes.AbstractCardEnum.CRN_DERIVATIONS;
import static Thmod.pathes.ThmodClassEnum.CRN_CLASS;

@SpireInitializer
public class Thmod implements
        RelicGetSubscriber,
        PostPowerApplySubscriber,
        PostExhaustSubscriber,
        PostBattleSubscriber,
        PostDungeonInitializeSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
        EditRelicsSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        OnCardUseSubscriber,
        EditKeywordsSubscriber,
        OnPowersModifiedSubscriber,
        PostDrawSubscriber,
        PostEnergyRechargeSubscriber,
        AddAudioSubscriber {
    private static final String MOD_BADGE = "img/UI/badge.png";
    private static final String ATTACK_CC = "img/512/bg_attack_CRN_s.png";
    private static final String SKILL_CC = "img/512/bg_skill_CRN_s.png";
    private static final String POWER_CC = "img/512/bg_power_CRN_s.png";
    private static final String ENERGY_ORB_CC = "img/512/CRNOrb.png";

    private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_CRN.png";
    private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_CRN.png";
    private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_CRN.png";
    private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/CRNOrb.png";
    public static final String CARD_ENERGY_ORB = "img/UI/energyOrb.png";
    //选英雄界面的角色图标、选英雄时的背景图片
    private static final String MY_CHARACTER_BUTTON = "img/charSelect/CRNButton.png";
    private static final String CRN_PORTRAIT = "img/charSelect/CRNPortrait.png";
    private static final String KEYWORD_STRING_ZHT = "localization/ThMod_CRN_keywords-zht.json";
    private static final String KEYWORD_STRING_ZHS = "localization/ThMod_CRN_keywords-zh.json";
    private static final String KEYWORD_STRING_JPN = "localization/ThMod_CRN_keywords-jp.json";

    public static final Color SILVER = CardHelper.getColor(200, 200, 200);
    private ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();
    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();

    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());

    public Thmod(){
        BaseMod.subscribe((ISubscriber)this);
        BaseMod.addColor(
                CRN_COLOR,
                SILVER,
                SILVER,
                SILVER,
                SILVER,
                SILVER,
                SILVER,
                SILVER,
                ATTACK_CC,
                SKILL_CC,
                POWER_CC,
                ENERGY_ORB_CC,
                ATTACK_CC_PORTRAIT,
                SKILL_CC_PORTRAIT,
                POWER_CC_PORTRAIT,
                ENERGY_ORB_CC_PORTRAIT,
                CARD_ENERGY_ORB);
        BaseMod.addColor(
                CRN_DERIVATIONS,
                SILVER,
                SILVER,
                SILVER,
                SILVER,
                SILVER,
                SILVER,
                SILVER,
                ATTACK_CC,
                SKILL_CC,
                POWER_CC,
                ENERGY_ORB_CC,
                ATTACK_CC_PORTRAIT,
                SKILL_CC_PORTRAIT,
                POWER_CC_PORTRAIT,
                ENERGY_ORB_CC_PORTRAIT,
                CARD_ENERGY_ORB);
    }

    @Override
    public void receiveEditCharacters(){
        logger.info("===================初始化角色==================");
        BaseMod.addCharacter(
                new CRN("CRN"),
                MY_CHARACTER_BUTTON,
                CRN_PORTRAIT,
                CRN_CLASS);
        logger.info("===================完成角色==================");
    }

    public static void initialize(){
        logger.info("===================开始初始化==================");
        new Thmod();
        logger.info("===================完成初始化==================");
    }

    @Override
    public void receiveEditCards(){
        logger.info("===================开始加入卡牌==================");
        loadCardsToAdd();
        for (AbstractCard card : this.cardsToAdd) {
            BaseMod.addCard(card);
        }
        logger.info("===================完成加入卡牌==================");
    }

    @Override
    public void receivePostExhaust(AbstractCard c){}

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower pow, AbstractCreature target, AbstractCreature owner){}

    @Override
    public void receivePowersModified() {}

    @Override
    public void receivePostDungeonInitialize() {}

    @Override
    public void receivePostDraw(AbstractCard arg0) {}

    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }

    @Override
    public void receiveEditKeywords() {
        logger.info("===================加载关键字==================");
        String keywordsPath = null;
        switch (Settings.language){
            case ZHT:
                keywordsPath = KEYWORD_STRING_ZHT;
                break;
            case ZHS:
                keywordsPath = KEYWORD_STRING_ZHS;
                break;
            case JPN:
                keywordsPath = KEYWORD_STRING_JPN;
        }
        Gson gson = new Gson();
        Keywords keywords;
        keywords = gson.fromJson(loadJson(keywordsPath), Keywords.class);
        for (Keyword key : keywords.keywords) {
            BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
        }
        logger.info("===================完成关键字==================");
    }

    public void receiveEditStrings(){
        logger.info("===================开始语言==================");

        String relic="", card="", power="", potion="",event="",monster="";
        if(Settings.language == Settings.GameLanguage.ZHS){
            card = "localization/ThMod_CRN_cards-zh.json";
            relic = "localization/ThMod_CRN_relics-zh.json";
            power = "localization/ThMod_CRN_powers-zh.json";
            potion = "localization/ThMod_CRN_potions-zh.json";
            event = "localization/ThMod_CRN_events-zh.json";
            monster = "localization/ThMod_CRN_monsters-zh.json";
        }  //其他语言配置的JSON
        else if(Settings.language == Settings.GameLanguage.JPN){
            card = "localization/ThMod_CRN_cards-jp.json";
            relic = "localization/ThMod_CRN_relics-jp.json";
            power = "localization/ThMod_CRN_powers-jp.json";
            potion = "localization/ThMod_CRN_potions-jp.json";
            event = "localization/ThMod_CRN_events-jp.json";
            monster = "localization/ThMod_CRN_monsters-jp.json";
        }


        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String potionStrings = Gdx.files.internal(potion).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
        String monsterStrings = Gdx.files.internal(monster).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);
        String eventStrings = Gdx.files.internal(event).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
        logger.info("===================完成语言==================");

    }


    private void loadCardsToAdd() {
        logger.info("===================加载自定义卡牌==================");
        this.cardsToAdd.clear();
        this.cardsToAdd.add(new Strike_CRN());
        this.cardsToAdd.add(new Shoot());
        this.cardsToAdd.add(new Defend_CRN());
        this.cardsToAdd.add(new WirelessInterference());
        this.cardsToAdd.add(new LegShot());
        this.cardsToAdd.add(new DIYUSINoSupport());
        this.cardsToAdd.add(new SIYUDINoSupport());
        this.cardsToAdd.add(new Disintegration());
        this.cardsToAdd.add(new Knifegame());
        this.cardsToAdd.add(new ThreeNiangRikyo());
        this.cardsToAdd.add(new Strafing());
        this.cardsToAdd.add(new Rest());
        this.cardsToAdd.add(new TeamOfThree());
        this.cardsToAdd.add(new SIKNoSupport());
        this.cardsToAdd.add(new SlashOfKatana());
        this.cardsToAdd.add(new Reload());
        this.cardsToAdd.add(new Seek());
        this.cardsToAdd.add(new Explore());
        this.cardsToAdd.add(new HNSNoSupport());
        this.cardsToAdd.add(new SupportFire());
        this.cardsToAdd.add(new Doubleslain());
        this.cardsToAdd.add(new DefensiveCounterattack());
        this.cardsToAdd.add(new Intercept());
        this.cardsToAdd.add(new Snipe());
        this.cardsToAdd.add(new ThroughShooting());
        this.cardsToAdd.add(new BrakingArmor());
        this.cardsToAdd.add(new WirelessCalling());
        this.cardsToAdd.add(new Reforming());
        this.cardsToAdd.add(new SwordSweep());
        this.cardsToAdd.add(new Catapult());
        this.cardsToAdd.add(new Accuration());
        this.cardsToAdd.add(new Replenishment());
        this.cardsToAdd.add(new RandomCall());
        this.cardsToAdd.add(new DIYUSI_DS());
        this.cardsToAdd.add(new SIYUDI_DS());
        this.cardsToAdd.add(new HNS_DS());
        this.cardsToAdd.add(new RMA_DS());
        this.cardsToAdd.add(new MDCN_DS());
        this.cardsToAdd.add(new KingdomOfWalkers());
        this.cardsToAdd.add(new MotorVehicleAttention());
        this.cardsToAdd.add(new CyclingByBike());
        this.cardsToAdd.add(new RPG());
        this.cardsToAdd.add(new Sharpness());
        this.cardsToAdd.add(new Assault());
        this.cardsToAdd.add(new Thump());
        this.cardsToAdd.add(new Converging());
        this.cardsToAdd.add(new WarOfDADDY());
        this.cardsToAdd.add(new MoKu());
        this.cardsToAdd.add(new SwingingTraverse());
        this.cardsToAdd.add(new TwoFriends());
        this.cardsToAdd.add(new Conversation());
        this.cardsToAdd.add(new Deathmatch());
        this.cardsToAdd.add(new BarrierProtection());
        this.cardsToAdd.add(new Quest());
        this.cardsToAdd.add(new Completed());
        this.cardsToAdd.add(new ConfusingCombatants());
        this.cardsToAdd.add(new MultipleCalls());
        this.cardsToAdd.add(new HazardSign());
        this.cardsToAdd.add(new Laser());
        this.cardsToAdd.add(new Lightning());
        this.cardsToAdd.add(new AttentionToFallingObjects());
        this.cardsToAdd.add(new Corrosivity());
        this.cardsToAdd.add(new Fire_SI());
        this.cardsToAdd.add(new RadioactiveSubstance());

        //this.cardsToAdd.add(new Confused());
        //this.cardsToAdd.add(new Exhaustion());
        //this.cardsToAdd.add(new Fetter());
        //this.cardsToAdd.add(new Lonely());
        this.cardsToAdd.add(new PoorSignal());

        this.cardsToAdd.add(new KyuBanCha());
        this.cardsToAdd.add(new FuryCutter());
        this.cardsToAdd.add(new MagneticFieldBurst());
        this.cardsToAdd.add(new Trench());
        this.cardsToAdd.add(new ExternalAssistance());
        this.cardsToAdd.add(new ReactiveBlock());
        this.cardsToAdd.add(new Wheatmeal());
        this.cardsToAdd.add(new QuickCall());
        this.cardsToAdd.add(new HighAndDry());
        this.cardsToAdd.add(new ThePostureOfBattle());
        this.cardsToAdd.add(new Combo());
        this.cardsToAdd.add(new CallOfGunfire());
        this.cardsToAdd.add(new Milke());
        this.cardsToAdd.add(new USE());
        this.cardsToAdd.add(new DontUse());
        this.cardsToAdd.add(new Sisters());
        this.cardsToAdd.add(new Jakenyoru());
        this.cardsToAdd.add(new Dodge());
        this.cardsToAdd.add(new inmhito_c());
        this.cardsToAdd.add(new Ijiritaosu());
        this.cardsToAdd.add(new Haisuinojin());
        this.cardsToAdd.add(new BoShinSenSo());
        this.cardsToAdd.add(new OffSide());
        this.cardsToAdd.add(new SuKiARi());
        this.cardsToAdd.add(new Endurance());
        this.cardsToAdd.add(new GoIsDead());
        this.cardsToAdd.add(new FuSiChao());
        this.cardsToAdd.add(new Xuli());
        this.cardsToAdd.add(new SZ_DS());
        this.cardsToAdd.add(new SIK_DS());
        this.cardsToAdd.add(new ICG_DS());
    }

    @Override
    public void receiveEditRelics() {
        logger.info("===================加载遗物==================");
        //将自定义的遗物添加到这里
        BaseMod.addRelicToCustomPool(new Radio(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new inmhito(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new Nico(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new Turioba(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new Zailiaoya(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new Biim(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new CiXing(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new Kururu(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new sakenomi(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new erohon(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new Juanru(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new tomare(), CRN_COLOR);
        BaseMod.addRelicToCustomPool(new yueyao(), CRN_COLOR);
    }

    public void receiveAddAudio() {
        BaseMod.addAudio("GUN1", "audio/hermit_gun.ogg");
        BaseMod.addAudio("GUN2", "audio/hermit_gun2.ogg");
        BaseMod.addAudio("GUN3", "audio/hermit_gun3.ogg");
        BaseMod.addAudio("SPIN", "audio/hermit_spin.ogg");
        BaseMod.addAudio("RELOAD", "audio/hermit_reload.ogg");
        BaseMod.addAudio("train", "audio/train.wav");
    }

    @Override
    public void receiveRelicGet(AbstractRelic relic) {
        logger.info("===================遗物设置==================");
        //移除遗物,这里移除了小屋子，太垃圾了。
        if (Objects.equals(AbstractDungeon.player.name, "CRN")) {
            AbstractDungeon.shopRelicPool.remove("TinyHouse");
        }
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard){
        logger.info("===================receiveCarded==================");
    }

    @Override
    public void receivePostBattle(AbstractRoom r){
        logger.info("===================PostBattle==================");
    }

    @Override
    public void receivePostInitialize(){
        final ModPanel settingsPanel = new ModPanel();
        logger.info("========================= receivePostInitialize =========================");

        BaseMod.addPotion(
                BeerBeer.class,
                Color.BLUE.cpy(),
                Color.YELLOW.cpy(),
                Color.NAVY,
                "BeerBeer",
                CRN_CLASS
        );

        BaseMod.addMonster("kawai", kawai_m.ID, () -> new kawai_m());
        //BaseMod.addMonsterEncounter(Exordium.ID, new MonsterInfo(kawai_m.ID, 99));

        BaseMod.addMonster("DIYUSI", DIYUSI_m.ID, () -> new DIYUSI_m());
        //BaseMod.addMonsterEncounter(Exordium.ID, new MonsterInfo(DIYUSI_m.ID, 99));


        final Texture badge = ImageMaster.loadImage(MOD_BADGE);
        BaseMod.registerModBadge(
                badge,
                "CRNMod",
                "ECUsam",
                "COOKIE mod CRN.ver",
                settingsPanel
        );

        BaseMod.addEvent(SZSIYUDI.ID, SZSIYUDI.class, Exordium.ID);

    }



    @Override
    public void receivePostEnergyRecharge(){
        logger.info("===================PostEnergy==================");
        for(AbstractCard c : recyclecards){
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }



    static class Keywords {
        Keyword[] keywords;
    }

    public static AbstractCard getRandomCRNCard() {
        AbstractCard card;
        int rng = AbstractDungeon.miscRng.random(0, 100);
        if (rng == 15) {
            card = new BrakingArmor();
        } else {
            card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        }
        return card;
    }
}
