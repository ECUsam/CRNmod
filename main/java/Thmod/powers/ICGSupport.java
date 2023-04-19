package Thmod.powers;

import Thmod.Thmod;
import Thmod.cards.gaba;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class ICGSupport extends AbstractPower {
    public static final String POWER_ID = "ICGSupport";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static int BolckNumber = 3;

    public ICGSupport(AbstractCreature owner, int amount){
        Thmod.logger.info("ICG支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/ICGSupport.png");
        Thmod.logger.info("ICG支援初始化完成");
    }
    public void atStartOfTurnPostDraw(){
        Thmod.logger.info("ICG支援生效");
        //flash();
        addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
        //TODO:加入GABA物理手牌
        addToTop(new MakeTempCardInHandAction(new gaba(), 1));
    }
    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
    }
    public void updateDescription() {
        Thmod.logger.info("ICG支援：更新描述");
        this.description = (DESCRIPTIONS[0]);
        Thmod.logger.info("ICG支援：更新描述完成");
    }
}
