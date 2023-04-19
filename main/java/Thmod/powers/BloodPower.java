package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static Thmod.pathes.CustomTags.SHOTS;

public class BloodPower extends AbstractPower {
    public static final String POWER_ID = "BloodPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;

    public BloodPower(AbstractCreature owner, int amount){
        Thmod.logger.info("BloodPower初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        updateDescription();
        this.img = new Texture("img/powers/BloodPower.png");
        Thmod.logger.info("BloodPower初始化完成");
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.justApplied = true;
        }
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }


    public void onUseCard(AbstractCard card, UseCardAction action) {
        RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
        int R = randomXS128.nextInt(100);
        if(card.hasTag(SHOTS) && R<=50){
            addToBot(new ApplyPowerAction(owner, AbstractDungeon.player, new BloodPower(owner, 1)));
        }
    }

    @Override
    public void atEndOfRound() {

        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.owner.damage(new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.HP_LOSS));
        }

        if (this.justApplied) {
            this.justApplied = false;
        } else {
            if (this.amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "BloodPower"));
            } else {
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "BloodPower", 1));
            }

        }
    }


    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
}
