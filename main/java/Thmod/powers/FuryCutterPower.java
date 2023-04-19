package Thmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Thmod.pathes.CustomTags.SLAIN;

public class FuryCutterPower extends AbstractPower {
    public static final String POWER_ID = "FuryCutterPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int magic;

    public FuryCutterPower(AbstractCreature owner, int amount, int magic) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.magic = magic;
        this.type = PowerType.DEBUFF;
        this.description = DESCRIPTIONS[0] + this.amount*this.magic + DESCRIPTIONS[1];
        this.img = new Texture("img/powers/FuryCutterPower.png");
    }

    public void onUseCard(AbstractCard card, UseCardAction action){
        DamageInfo info = new DamageInfo(this.owner, this.amount*this.magic);
        if(card.hasTag(SLAIN)){
            this.addToTop(new DamageAction(this.owner, info, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if(this.amount == 0){
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, FuryCutterPower.POWER_ID));
        }
        this.description = DESCRIPTIONS[0] + this.amount*this.magic + DESCRIPTIONS[1];
        updateDescription();
    }

}
