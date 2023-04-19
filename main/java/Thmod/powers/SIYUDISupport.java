package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class SIYUDISupport extends AbstractPower {
    public static final String POWER_ID = "SIYUDISupport";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int Damage = 8;
    private final DamageInfo info;


    public SIYUDISupport(AbstractCreature owner, int amount){
        Thmod.logger.info("SIYUDI支援初始化");
        this.info = new DamageInfo(player, Damage);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/SIYUDISupport.png");
        Thmod.logger.info("SIYUDI支援初始化完成");
    }

    public void atEndOfTurn(boolean isPlayer){
        Thmod.logger.info("SIYUDI支援生效");
        if(isPlayer){
            //flash();
            for(int i=0;i<this.amount;i++) {
                AbstractCreature target;

                target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                DamageInfo info = new DamageInfo(target, Damage, DamageInfo.DamageType.THORNS);
                if (target != null && !target.halfDead && !target.isDead) {
                    this.addToTop(new DamageAction(target, info, AbstractGameAction.AttackEffect.FIRE));
                }
            }
        }

    }
    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "SIYUDISupport"));
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
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, SIYUDISupport.POWER_ID));
        }
    }
    public void updateDescription() {
        Thmod.logger.info("DIYUSI支援：更新描述");
        this.description = (DESCRIPTIONS[0]);
        Thmod.logger.info("DIYUSI支援：更新描述完成");
    }
}
