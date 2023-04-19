package Thmod.monsters;

import Thmod.Thmod;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class kawai_m extends AbstractMonster {


    public static final String ID = "kawai";
    private static final String NAME = "kawai";
    private static final int HP = 10;
    private static final int HPUP = 10;
    private static final int BLOCK = 10;
    //private static final int BLOCK_UP = 15;
    private static final int DMG = 8;
    private static final int DMG_MULTI = 3;

    private static final String MODEL_ATLAS = "img/monsters/kawai/kawai.atlas";
    private static final String MODEL_JSON = "img/monsters/kawai/kawai.json";
    public int turnNum = 0;
    public int block;
    private int attackDmg;


    public kawai_m(float x, float y){
        super(
                NAME,
                ID,
                HP,
                0.0F,
                -30.0F,
                220.0F,
                250.0F,
                null,
                x,
                y
        );

        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.name = "可爱先辈";
        }
        if(AbstractDungeon.ascensionLevel >=8){
            setHp(HPUP);
        }
        this.type = EnemyType.NORMAL;

        if(AbstractDungeon.ascensionLevel >= 2){
            this.attackDmg = 12;
        }else {
            this.attackDmg = 10;
        }

        this.damage.add(new DamageInfo(this, DMG));
        this.damage.add(new DamageInfo(this, this.attackDmg));
        this.damage.add(new DamageInfo(this, DMG_MULTI));
        this.block = BLOCK;

        loadAnimation(MODEL_ATLAS, MODEL_JSON, 1.2f);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime()* MathUtils.random());
    }

    public kawai_m(){this(0.0f, -1.0f);}

    public void usePreBattleAction(){
        AbstractDungeon.getCurrRoom().cannotLose = true;
    }
    @Override
    public void takeTurn() {
        AbstractPlayer player = AbstractDungeon.player;
        switch (this.nextMove){
            case 1:
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(
                                player
                                , this.damage.get(0)
                        ));
                break;
            case 2:
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    int block = this.block;
                    if (!m.isDeadOrEscaped()) {
                        AbstractDungeon.actionManager.addToBottom(
                                new GainBlockAction(m, this, block)
                        );
                    }
                }
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(
                                player
                                , this.damage.get(2)
                        ));
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(
                                player
                                , this.damage.get(2)
                        ));
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(
                                player
                                , this.damage.get(2)
                        ));
            default:
                Thmod.logger.info(
                        "可爱先辈 : takeTurn : Error : Action number "
                                + this.nextMove
                                + " should never be called."
                );
                break;
        }
        //AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    private void setAttackAction() {
        if (this.turnNum < 2) {
            setMove((byte) 1, Intent.ATTACK_DEBUFF, DMG);
        } else {
            setMove((byte) 3, Intent.ATTACK, DMG_MULTI, 3, true);
        }
    }


    protected void getMove(int num) {
        this.turnNum++;
        if (this.turnNum < 3) {

            if (num <= 50) {
                setAttackAction();
            } else {
                setDefendAction();
            }
        } else {
            setAttackAction();
        }
    }

    private void setDefendAction() {
        setMove((byte) 2, Intent.DEFEND_BUFF);
    }




}
