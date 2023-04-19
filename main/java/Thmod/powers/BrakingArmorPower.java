package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BrakingArmorPower extends AbstractPower {
    public static final String POWER_ID = "BrakingArmorPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BrakingArmorPower(AbstractCreature owner, int amount){
        Thmod.logger.info("BrakingArmorPower初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/BrakingArmorPower.png");
        Thmod.logger.info("BrakingArmorPower初始化完成");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractCreature player = AbstractDungeon.player;
        if(isPlayer && player.hasPower("Support")){
            AbstractDungeon.actionManager.addToBottom(
                    new GainBlockAction(
                            this.owner,
                            this.owner,
                            this.owner.getPower("Support").amount*this.amount
                            ));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "BrakingArmorPower"));
        }
    }


    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

}
