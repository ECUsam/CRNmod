package Thmod.characters;

import Thmod.Thmod;
import Thmod.cards.*;
import Thmod.effect.VictoryEffect;
import Thmod.powers.LianZhao;
import Thmod.powers.Precise;
import Thmod.relics.erohon;
import Thmod.relics.yueyao;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.ThmodClassEnum;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Objects;

import static Thmod.pathes.CustomTags.SHOTS;
import static Thmod.pathes.CustomTags.SLAIN;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class CRN extends CustomPlayer {

    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    private static final int ENERGY_PER_TURN = 3;
    private static final String CRN_SHOULDER_2 = "img/char/CRN/shoulder2.png";
    private static final String CRN_SHOULDER_1 = "img/char/CRN/shoulder1.png";
    private static final String CRN_CORPSE = "img/char/CRN/fallen.png";
    private static final String CRN_SKELETON_ATLAS = "img/char/CRN/CRN.atlas";
    private static final String CRN_SKELETON_JSON = "img/char/CRN/CRN.json";
    private static final String CRN_ANIMATION = "Idle";

    public static final Color SILVER = CardHelper.getColor(0, 255, 255);

    private static final int STARTING_HP = 60;
    private static final int MAX_HP = 60;
    private static final int STARTING_GOLE = 200;
    private static final int HAND_SIZE = 5;
    private static final int ASCENSION_MAX_HP_LOSS = 4;
    private static final String[] ORB_TEXTURES = new String[] {
            "img/UI/EPanel/layer5.png",
            "img/UI/EPanel/layer4.png",
            "img/UI/EPanel/layer3.png",
            "img/UI/EPanel/layer2.png",
            "img/UI/EPanel/layer1.png",
            "img/UI/EPanel/layer0.png",
            "img/UI/EPanel/layer5d.png",
            "img/UI/EPanel/layer4d.png",
            "img/UI/EPanel/layer3d.png",
            "img/UI/EPanel/layer2d.png",
            "img/UI/EPanel/layer1d.png"
    };
    private static final String ORB_VFX = "img/UI/energyBlueVFX.png";
    private static final float[] LAYER_SPEED =
            {-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

    public CRN(String name) {
        super(name, ThmodClassEnum.CRN_CLASS, ORB_TEXTURES, ORB_VFX, LAYER_SPEED, null, null);

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        logger.info("=========初始化CRN=========");
        initializeClass(
                null,
                CRN_SHOULDER_2, // required call to load textures and setup energy/loadout
                CRN_SHOULDER_1,
                CRN_CORPSE,
                getLoadout(),
                0.0F, 8.0F, 230.0F, 360.0F,
                new EnergyManager(ENERGY_PER_TURN)
        );
        logger.info("=========初始化动画=========");
        loadAnimation(CRN_SKELETON_ATLAS, CRN_SKELETON_JSON, 2.9F);
        logger.info("=========加载完动画=========");
        AnimationState.TrackEntry e = this.state.setAnimation(0, CRN_ANIMATION, true);
        logger.info("=========AnimationState=========");
        e.setTime(e.getEndTime() * MathUtils.random());
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTimeScale(1.0F);
    }
    @Override
    public ArrayList<String> getStartingDeck(){
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Strike_CRN");
        retVal.add("Strike_CRN");
        retVal.add("Shoot");
        retVal.add("Shoot");
        retVal.add("Defend_CRN");
        retVal.add("Defend_CRN");
        retVal.add("Defend_CRN");
        retVal.add("Defend_CRN");
        retVal.add("WirelessInterference");
        retVal.add("LegShot");

        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("inmhito");
        UnlockTracker.markRelicAsSeen("inmhito");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout(){
        String title="";
        String flavor="";
        if(Settings.language == Settings.GameLanguage.ZHS){
            title = "CRN";
            flavor= "朽木市的雇佣兵， NL 接受任务前来调查高塔。";
        } else if (Settings.language == Settings.GameLanguage.JPN) {
            title = "CRN";
            flavor= "朽木市の雇佣兵， NL 任務を受け、塔の調査を。";
        }

        return new CharSelectInfo(title, flavor, STARTING_HP, MAX_HP, 0, STARTING_GOLE, HAND_SIZE, this, getStartingRelics(), getStartingDeck(), false);
    }



    @Override
    public String getTitle(PlayerClass playerClass){
        return "CRN";
    }
    @Override
    public AbstractCard.CardColor getCardColor(){
        return AbstractCardEnum.CRN_COLOR;
    }


    @Override
    public Color getCardRenderColor(){
        return SILVER;
    }


    @Override
    public AbstractCard getStartCardForEvent(){
        return new Explore();
    }

    @Override
    public Color getCardTrailColor(){
        return SILVER;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }


    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("GUN2", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(
                ScreenShake.ShakeIntensity.MED,
                ScreenShake.ShakeDur.SHORT,
                false
        );
    }

    @Override
    public void updateVictoryVfx(ArrayList<AbstractGameEffect> effects) {
        super.updateVictoryVfx(effects);
        Thmod.logger.info("胜利画面");
        boolean X = false;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c instanceof Strike_CRN) {
                X = true;
                break;
            }
        }
        if (X) {
            boolean foundEyeVfx = false;
            for (AbstractGameEffect e : effects) {
                if (e instanceof VictoryEffect) {

                    foundEyeVfx = true;
                    break;
                }
            }
            if (!foundEyeVfx) {
                effects.add(new VictoryEffect());
            }
        }
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "BLUNT_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "CRN";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new CRN(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return "你抬起手中的枪";
    }

    @Override
    public Color getSlashAttackColor() {
        return SILVER;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
                AbstractGameAction.AttackEffect.SLASH_HEAVY,
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        };
    }

    @Override
    public void damage(DamageInfo info) {
        super.damage(info);
        if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (
                info.output - this.currentBlock > 0)) {
            AnimationState.TrackEntry e =
                    this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);
            e.setTimeScale(1.0F);
        }
        Thmod.logger.info(info+"damageCRN");

    }

    @Override
    public void useCard(AbstractCard targetCard, AbstractMonster monster, int energyOnUse){
        super.useCard(targetCard, monster, energyOnUse);
        if(Objects.equals(targetCard.cardID, Xuli.ID)&&player.hasPower(LianZhao.POWER_ID))return;
        if(targetCard.hasTag(SLAIN)){
            int num = 1;
            if(player.hasRelic(yueyao.ID))num = 3;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                    new LianZhao(player, num), num));
            actionManager.addToBottom(new RemoveSpecificPowerAction(player, player, Precise.POWER_ID));
            return;
        } else if (targetCard.hasTag(SHOTS)) {
            if(!player.hasRelic(yueyao.ID)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                        new Precise(player, 1), 1));
            }
            actionManager.addToBottom(new RemoveSpecificPowerAction(player, player, LianZhao.POWER_ID));
            RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
            int R = randomXS128.nextInt(3);
            Thmod.logger.info("播放枪击音效，R="+R);
            switch (R) {
                case 0:
                    CardCrawlGame.sound.playV("GUN1", 1.25F);
                    break;
                case 1:
                    CardCrawlGame.sound.playV("GUN2", 1.25F);
                    break;
                case 2:
                    CardCrawlGame.sound.playV("GUN3", 1.25F);
                    break;
            }
            return;
        }

        RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
        int R = randomXS128.nextInt(100);
        if(player.hasRelic(erohon.ID) && R<=50){
            return;
        }

        if(player.hasPower(LianZhao.POWER_ID)){
            actionManager.addToBottom(new RemoveSpecificPowerAction(player, player, LianZhao.POWER_ID));
        }
        if(player.hasPower(Precise.POWER_ID)){
            actionManager.addToBottom(new RemoveSpecificPowerAction(player, player, Precise.POWER_ID));
        }

    }




    @Override
    public String getVampireText() {
        return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[1];
    }



}
