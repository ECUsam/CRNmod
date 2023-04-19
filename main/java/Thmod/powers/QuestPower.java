package Thmod.powers;

import Thmod.actions.RecallAction;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class QuestPower extends AbstractPower {
    public static final String POWER_ID = "QuestPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public QuestPower(AbstractCreature owner){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/QuestPower.png");
    }

    public void atStartOfTurnPostDraw(){
        if (owner.hasPower("Support") && owner.getPower("Support").amount >= 3 ){
            owner.getPower("Support").reducePower(3);
            AbstractDungeon.actionManager.addToBottom(
                    new RecallAction());
        }

    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

}
