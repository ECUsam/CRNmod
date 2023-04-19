package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static Thmod.pathes.CustomTags.SLAIN;

public class SIKSupport extends AbstractPower {

    public static final String POWER_ID = "SIKSupport";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    //public boolean SLAINSET = false;

    public SIKSupport(AbstractCreature owner, int amount){
        Thmod.logger.info("SIK支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/SIKSupport.png");
        Thmod.logger.info("SIK支援初始化完成");

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(SLAIN)) {
                c.baseDamage = CardLibrary.getCard(c.cardID).baseDamage + 3*this.amount;
            }
        }
    }
    //TODO：出BUG辣

    public void onDrawOrDiscard() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(SLAIN)) {
                c.baseDamage = CardLibrary.getCard(c.cardID).baseDamage + 3*this.amount;
            }
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
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, SIKSupport.POWER_ID));
        }
    }

/*
        for(AbstractCard card : AbstractDungeon.player.hand.group){
            Thmod.logger.info("SIK支援isDamageModified"+card.isDamageModified);
            if(card.hasTag(SLAIN)&& !card.isDamageModified){
                card.baseDamage += 3;
            }
        }

    public void onCardDraw(AbstractCard card){
        Thmod.logger.info("SIK支援onCardDraw");
        Thmod.logger.info("SIK支援isDamageModified"+card.isDamageModified);
        if(card.hasTag(SLAIN)&& !card.isDamageModified){
            card.baseDamage += 3*this.amount;
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(SLAIN) && !card.isDamageModified) {
            card.baseDamage -= 3 * this.amount;
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action){
        if(card.hasTag(SLAIN)){
            Thmod.logger.info("SIK支援SLAIN为TRUE");
            SLAINSET= true;
        }
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if(type == DamageInfo.DamageType.NORMAL && SLAINSET){
            Thmod.logger.info("SIK支援增加伤害生效");
            SLAINSET = false;
        return damage + 5.0F*this.amount;
        }else {
            SLAINSET = false;
            return damage;
        }
    }
*/
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
