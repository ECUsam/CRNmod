package Thmod.powers;

import Thmod.Thmod;
import Thmod.pathes.CustomTags;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AccurationPower extends AbstractPower {
    public static final String POWER_ID = "AccurationPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public AccurationPower(AbstractCreature owner, int amount){
        Thmod.logger.info("AccurationPower支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/AccurationPower.png");
        Thmod.logger.info("AccurationPower初始化完成");
    }


    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        Thmod.logger.info("CardInfo："+card.name);
        Thmod.logger.info("atDamageFinalGive");
        if(card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SHOTS)) {
            Thmod.logger.info("damage:"+damage+"\ntype:"+type);
            return (float) (damage*(1+0.3*this.amount));
        }
        else {
            return damage;
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
}
