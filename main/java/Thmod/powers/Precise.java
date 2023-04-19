package Thmod.powers;

import Thmod.Thmod;
import Thmod.pathes.CustomTags;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Precise extends AbstractPower {
    public static final String POWER_ID = "Precise";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public int Random = 0;
    public boolean isCri = false;
    public RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;


    public Precise(AbstractCreature owner, int amount){
        Thmod.logger.info("Precise支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/Precise.png");
        Thmod.logger.info("Precise初始化完成");
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        Thmod.logger.info("onUseCard发动");
        if (card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SHOTS)) {
            if (this.amount == 0 || isCri) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Precise"));
            }
        }
    }

    public void onInitialApplication(){
        Thmod.logger.info("onInitialApplication发动"+"R:"+Random);
        Random = randomXS128.nextInt(100);
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        Thmod.logger.info("precise:CardInfo："+card.name);
        //Thmod.logger.info("atDamageGive发动");
        if(card.type == AbstractCard.CardType.ATTACK && !card.purgeOnUse && this.amount > 0 && card.hasTag(CustomTags.SHOTS)) {
            if (Random < this.amount * 5) {
                isCri = true;
                return damage*3.0f;
            }else return damage;
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
        Thmod.logger.info("onInitialApplication发动"+"R:"+Random);
        Random = randomXS128.nextInt(100);
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]+this.amount*5+DESCRIPTIONS[1]);
    }
}
