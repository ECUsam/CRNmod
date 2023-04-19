package Thmod.powers;

import Thmod.Thmod;
import Thmod.actions.CorrosivityAction;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CorrosivityPower extends AbstractPower {
    public static final String POWER_ID = "CorrosivityPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public CorrosivityPower(AbstractCreature owner){
        Thmod.logger.info("SIYUDI支援初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/CorrosivityPower.png");
        Thmod.logger.info("SIYUDI支援初始化完成");
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(
                    new CorrosivityAction());
        }
    }



    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }
}
