package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LightningPower extends AbstractPower {
    public static final String POWER_ID = "LightningPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int Damage = 14;
    private final DamageInfo info;


    public LightningPower(AbstractCreature owner, int amount){
        this.info = new DamageInfo(AbstractDungeon.player, Damage);
        this.name = NAME;
        this.amount = amount;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/LightningPower.png");
    }

    public void atEndOfTurn(boolean isPlayer){
        Thmod.logger.info("闪电生效");
        if(isPlayer){
            for(int i=0;i<this.amount;i++) {
                AbstractCreature target;

                target = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                DamageInfo info = new DamageInfo(target, Damage);
                if (target != null) {
                    this.addToTop(new DamageAction(target, info, AbstractGameAction.AttackEffect.LIGHTNING));
                }
            }

        }

    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
}
