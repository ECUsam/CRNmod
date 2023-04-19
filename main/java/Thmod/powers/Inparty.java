package Thmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;



import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Inparty extends AbstractPower {
    public static final String POWER_ID = "Inparty";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Inparty(AbstractCreature owner, int amount){

        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        updateDescription();
        this.img = new Texture("img/powers/Inparty.png");

    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            int num = player.getPower(Support.POWER_ID).amount;
            this.owner.damage(new DamageInfo(this.owner, num, DamageInfo.DamageType.HP_LOSS));
            this.amount--;
            if (this.amount <= 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "BloodPower"));
            }
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
}
