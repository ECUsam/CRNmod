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

public class LianZhao extends AbstractPower {
    public static final String POWER_ID = "LianZhao";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public LianZhao(AbstractCreature owner, int amount){
        Thmod.logger.info("LianZhao支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/LianZhao.png");
        Thmod.logger.info("LianZhao初始化完成");
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        Thmod.logger.info("onUseCard发动");
        if (card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SLAIN)) {
            if (this.amount == 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "LianZhao"));
            }
        }
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        Thmod.logger.info("CardInfo："+card.name);
        Thmod.logger.info("atDamageGive发动lianzhao");
        if(card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SLAIN)) {
            Thmod.logger.info("damage:"+damage+"\ntype:"+type);
            return (float) (damage*(1+0.1*this.amount));
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
        this.description = (DESCRIPTIONS[0]+this.amount*10+DESCRIPTIONS[1]);
    }
}
