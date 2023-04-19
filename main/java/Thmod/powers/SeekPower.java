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

public class SeekPower extends AbstractPower {
        public static final String POWER_ID = "SeekPower";
        private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public SeekPower(AbstractCreature owner, int amount){
        Thmod.logger.info("SeekPower支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/SeekPower.png");
        Thmod.logger.info("SeekPower初始化完成");
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        Thmod.logger.info("onUseCard发动");
        if (card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SHOTS)) {
            amount -= 1 ;
            if (this.amount == 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "SeekPower"));
            }
        }
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        Thmod.logger.info("CardInfo："+card.name);
        Thmod.logger.info("atDamageGive发动seek");
        if(card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SHOTS)) {
            Thmod.logger.info("damage:"+damage+"\ntype:"+type);
            return damage*2;
        }
        else {
            return damage;
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
}