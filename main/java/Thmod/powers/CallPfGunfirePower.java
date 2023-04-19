package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Thmod.pathes.CustomTags.SHOTS;

public class CallPfGunfirePower extends AbstractPower {
    public static final String POWER_ID = "CallPfGunfirePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int magic;

    public CallPfGunfirePower(AbstractCreature owner, int amount, int magicnm){
        Thmod.logger.info("BrakingArmorPower初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.magic = magicnm;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/CallPfGunfirePower.png");
        Thmod.logger.info("CallPfGunfirePower初始化完成");
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.hasTag(SHOTS))this.amount +=1;
        if(this.amount%2 == 0 && this.amount>0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new Support(owner, this.magic), this.magic));
            this.amount = 0;
        }
    }

    @Override
    public void stackPower(int stackAmount) {

    }


    public void updateDescription() {
        if(this.magic<3)this.description = (DESCRIPTIONS[0]);
        if(this.magic>=3)this.description = DESCRIPTIONS[1];
    }
}
