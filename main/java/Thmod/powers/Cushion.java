package Thmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Cushion extends AbstractPower {
    public static final String POWER_ID = "Cushion";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Cushion(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/Cushion.png");
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount){
        if(damageAmount>0){
            addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        }
        return damageAmount/2;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Cushion"));
        }
    }
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

}
