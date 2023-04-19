package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Thmod.pathes.CustomTags.SLAIN;

public class SharpnessPower extends AbstractPower {
    public static final String POWER_ID = "SharpnessPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SharpnessPower(AbstractCreature owner){
        Thmod.logger.info("SharpnessPower初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/SharpnessPower.png");
        Thmod.logger.info("SharpnessPower初始化完成");
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster monster) {
        if(card.hasTag(SLAIN)){
            RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
            int R = randomXS128.nextInt(100);
            Thmod.logger.info("SharpnessR:"+R);
            if(R <= 20){
                addToBot(new ApplyPowerAction(monster, owner, new BloodPower(monster, 1)));
            }
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

}
