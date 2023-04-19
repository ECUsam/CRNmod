package Thmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReloadAction2 extends AbstractGameAction {
    public void update(){
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
        tickDuration();

        if(this.isDone){
            for(AbstractCard c : DrawCardAction.drawnCards){
                if(c.type != AbstractCard.CardType.ATTACK && !c.freeToPlayOnce){
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
            }
        }
        for(AbstractCard c : AbstractDungeon.player.hand.group){
            c.setCostForTurn(0);
        }
        this.isDone = true;
    }

}
