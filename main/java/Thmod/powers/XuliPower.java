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

public class XuliPower extends AbstractPower {
    public static final String POWER_ID = "XuliPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int num;


    public XuliPower(AbstractCreature owner, int amount, int num){
        Thmod.logger.info("XuliPower支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.num = num;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/XuliPower.png");
        Thmod.logger.info("XuliPower初始化完成");
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        Thmod.logger.info("onUseCard发动");
        if (card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SLAIN)) {
            amount -= 1 ;
            if (this.amount == 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "XuliPower"));
            }
        }
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        Thmod.logger.info("CardInfo："+card.name);
        Thmod.logger.info("atDamageGive发动xuli");
        if(card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SLAIN)) {
            Thmod.logger.info("damage:"+damage+"\ntype:"+type);
            return damage+num;
        }
        else {
            return damage;
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
}
