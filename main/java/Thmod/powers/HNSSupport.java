package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class HNSSupport extends AbstractPower {
    public static final String POWER_ID = "HNSSupport";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final DamageInfo info;
    public static final int Damage = 10;

    public HNSSupport(AbstractCreature owner, int amount){
        Thmod.logger.info("HNS支援初始化");
        this.name = NAME;
        this.info = new DamageInfo(AbstractDungeon.player, Damage);
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/HNSSupport.png");
        Thmod.logger.info("HNS支援初始化完成");
    }

    public void atStartOfTurnPostDraw(){
        Thmod.logger.info("HNS支援生效");
        //flash();
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, 1), 1));
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
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, HNSSupport.POWER_ID));
        }
    }

    public void atEndOfTurn(boolean isPlayer){
        Thmod.logger.info("HNS支援生效2");
        if(isPlayer){
            //flash();
            for(int i=0;i<this.amount;i++) {
                for(AbstractCreature m : AbstractDungeon.getMonsters().monsters){
                    if(!m.isDead && !m.halfDead)addToBot(new DamageAction(m, new DamageInfo(m, 10), AbstractGameAction.AttackEffect.SLASH_HEAVY));
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
    }
    public void updateDescription() {
        Thmod.logger.info("DIYUSI支援：更新描述");
        this.description = (DESCRIPTIONS[0]);
        Thmod.logger.info("DIYUSI支援：更新描述完成");
    }

}
