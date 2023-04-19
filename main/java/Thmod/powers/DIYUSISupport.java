package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DIYUSISupport extends AbstractPower {
    public static final String POWER_ID = "DIYUSISupport";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static int BolckNumber = 4;

    public DIYUSISupport(AbstractCreature owner, int amount){
        Thmod.logger.info("DIYUSI支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/DIYUSISupport.png");
        Thmod.logger.info("DIYUSI支援初始化完成");

        //TODO：肯定有bug
        if(player.hasPower(NoDIYUSI.POWER_ID))addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, DIYUSISupport.POWER_ID));
    }

    public void atStartOfTurnPostDraw(){
        updateDescription();
        Thmod.logger.info("DIYUSI支援生效");
        //flash();
        for(int i=0;i<this.amount;i++) {
            player.addBlock((BolckNumber));

        }
        int x = (int)(Math.log(this.amount+1)/Math.log(2));
        AbstractDungeon.actionManager.addToBottom(
                new DrawCardAction(this.owner, x)
        );

    }

    public void reducePower(int reduceAmount) {
        updateDescription();
        if (this.amount - reduceAmount <= 0) {
            this.fontScale = 8.0F;
            this.amount = 0;
        } else {
            this.fontScale = 8.0F;
            this.amount -= reduceAmount;
        }
        if (this.amount == 0 || player.hasPower(NoDIYUSI.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, DIYUSISupport.POWER_ID));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        updateDescription();
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "DIYUSISupport"));
        }
    }
    public void updateDescription() {
        Thmod.logger.info("DIYUSI支援：更新描述");
        int x = (int)(Math.log(this.amount+1)/Math.log(2));
        this.description = (DESCRIPTIONS[0]+x+DESCRIPTIONS[1]+4*this.amount+DESCRIPTIONS[2]);
        Thmod.logger.info("DIYUSI支援：更新描述完成");
    }

}
