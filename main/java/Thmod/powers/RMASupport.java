package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RMASupport extends AbstractPower {

    public static final String POWER_ID = "RMASupport";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public RMASupport(AbstractCreature owner, int amount){
        Thmod.logger.info("RMA支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/RMASupport.png");
        Thmod.logger.info("RMA支援初始化完成");
    }

    public void atStartOfTurnPostDraw(){
        Thmod.logger.info("RMA支援获得能量");
        //flash();
        for(int i=0;i<this.amount;i++) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));

        }

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
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "RMASupport"));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "RMASupport"));
        }
    }
    public void updateDescription() {
        Thmod.logger.info("RMA支援：更新描述");
        this.description = (DESCRIPTIONS[0]);
        Thmod.logger.info("RMA支援：更新描述完成");
    }
}
