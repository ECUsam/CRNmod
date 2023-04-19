package Thmod.monsters;

import Thmod.effect.EmptyEffect;
import Thmod.effect.TrainEffect;
import Thmod.powers.NoDIYUSI;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

//TODO:在做了在做了
public class DIYUSI_m extends AbstractMonster {

    private int AT1;
    private final int AT2 = 12;
    private final int AT3 = 16;
    private final int AT4 = 20;
    private final int AT5 = 24;
    private final int AT6 = 50;
    private final int AT7 = 17;
    private final int AT8 = 20;
    private final int AT9 = 21;
    private int debuff;

    private int attackDmg;

    public static final String ID = "DIYUSI";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    private static final String NAME = monsterStrings.NAME;
    private static final int HP = 30;
    private String TEXT1;

    private static final String MODEL_ATLAS = "img/monsters/DIYUSI.atlas";
    private static final String MODEL_JSON = "img/monsters/DIYUSI.json";

    public DIYUSI_m() {
        super(NAME, ID, HP, 0.0F, 0.0F, 220.0F, 280.0F, null, -20.0F, 10.0F);

        this.type = EnemyType.NORMAL;

        loadAnimation(MODEL_ATLAS, MODEL_JSON, 2.1F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        e.setTimeScale(1.0F);

        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.TEXT1 = "回去...你应付不来这里";
        }
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp((int) (HP*1.3));
        } else {
            setHp(HP);
        }
        this.damage.add(new DamageInfo(this, 6));
        this.damage.add(new DamageInfo(this, this.AT1));
        this.damage.add(new DamageInfo(this, this.AT2));
        this.damage.add(new DamageInfo(this, this.AT3));
        this.damage.add(new DamageInfo(this, this.AT4));
        this.damage.add(new DamageInfo(this, this.AT5));
        this.damage.add(new DamageInfo(this, this.AT6));
        this.damage.add(new DamageInfo(this, this.AT7));
        this.damage.add(new DamageInfo(this, this.AT8));
        this.damage.add(new DamageInfo(this, this.AT9));


        if(AbstractDungeon.ascensionLevel >= 2){
            this.attackDmg = 12;
        }else {
            this.attackDmg = 10;
        }
        this.damage.add(new DamageInfo(this, this.attackDmg));
    }

    public void usePreBattleAction() {

        CardCrawlGame.music.silenceTempBgmInstantly();
        CardCrawlGame.music.playTempBgmInstantly("OFF  Peppersteak NITRO Remix.mp3", true);

        addToBot(new ApplyPowerAction(player, this, new NoDIYUSI(player)));
        //addToBot(new ApplyPowerAction(this, this, new FlightPower(this,3)));
    }

    @Override
    public void takeTurn() {
        int i;
        switch (this.nextMove) {
            case 1:
                CardCrawlGame.sound.playV("train", 1.25F);
                this.state.setAnimation(0, "train", false);
                this.state.addAnimation(0, "normal", true, 0.0F);
                for (i = 0; i < 3; i++) {
                    if (i == 0) {

                        AbstractDungeon.actionManager.addToBottom( new VFXAction(new TrainEffect(), 0.6F));
                    }else {
                        AbstractDungeon.actionManager.addToBottom(new VFXAction(new EmptyEffect(), 0.6F));
                    }
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
        }
    }

    @Override
    protected void getMove(int i) {
        setMove((byte)1, AbstractMonster.Intent.ATTACK, (this.damage.get(1)).base);

    }
}
