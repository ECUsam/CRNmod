package Thmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QuickCallPower extends AbstractPower {
    public static final String POWER_ID = "QuickCallPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int magic = 4;

    public QuickCallPower(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.DEBUFF;
        updateDescription();
        this.img = new Texture("img/powers/QuickCallPower.png");
    }

    public void atStartOfTurn() {
        this.amount--;
        updateDescription();
        if (this.amount == 0) {
            if(this.owner.hasPower(Support.POWER_ID)) {

                if(this.owner.getPower(Support.POWER_ID).amount<=this.magic){
                    addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Support"));
                }else {
                    this.owner.getPower("Support").reducePower(this.magic);
                }
            }

            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "QuickCallPower"));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        this.magic += 6;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, QuickCallPower.POWER_ID));
        }
    }


    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+this.magic+DESCRIPTIONS[2]);
    }

}
