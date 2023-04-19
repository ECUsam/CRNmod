package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Thmod.pathes.CustomTags.SLAIN;

public class Burning extends AbstractPower {
    public static final String POWER_ID = "Burning";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Burning(AbstractCreature owner, int amount){
        Thmod.logger.info("Burning初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        updateDescription();
        this.img = new Texture("img/powers/Burning.png");
        Thmod.logger.info("Burning初始化完成");
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        if(card.hasTag(SLAIN)){
            damage += this.amount*1.0F;
        }
        return this.atDamageReceive(damage, damageType);
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]);
    }

}