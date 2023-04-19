package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FirePower extends AbstractPower {
    public static final String POWER_ID = "FirePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int Damage = 6;

    public FirePower(AbstractCreature owner){
        Thmod.logger.info("SIYUDI支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/FirePower.png");
        Thmod.logger.info("SIYUDI支援初始化完成");
    }

    public void atEndOfTurn(boolean isPlayer){
        Thmod.logger.info("SIYUDI支援生效");
        if(isPlayer){
            //addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, Damage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            for(AbstractCreature m : AbstractDungeon.getMonsters().monsters){
                if(!m.isDead && !m.halfDead)addToBot(new DamageAction(m, new DamageInfo(m, Damage), AbstractGameAction.AttackEffect.FIRE));
            }
            for(AbstractCreature m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDead && !m.isDying) {
                    addToBot(new ApplyPowerAction(m, owner, new Burning(m, 2)));
                }
            }
        }

    }


    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
}
