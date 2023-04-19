package Thmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MilkPower extends AbstractPower {
    public static final String POWER_ID = "MilkPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MilkPower(AbstractCreature owner){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/MilkPower.png");
    }

    public void atStartOfTurn() {
        for(AbstractPower p : owner.powers){
            if(p.type == PowerType.DEBUFF){
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, p.ID));
            }
        }
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "MilkPower"));
    }

    public void reducePower(int reduceAmount) {
        if (this.amount - reduceAmount <= 0) {
            this.fontScale = 8.0F;
            this.amount = 0;
        } else {
            this.fontScale = 8.0F;
            this.amount -= reduceAmount;
        }
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, MilkPower.POWER_ID));
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

}
