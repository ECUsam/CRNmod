package Thmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class KNNSupport extends AbstractPower {
    public static final String POWER_ID = "KNNSupport";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public KNNSupport(AbstractCreature owner){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/KNNSupport.png");
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(damageAmount>owner.currentHealth){
            owner.heal((int)(owner.maxHealth*0.1F), true);
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, KNNSupport.POWER_ID));
            return 0;
        }
        return damageAmount;
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
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, KNNSupport.POWER_ID));
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

}
