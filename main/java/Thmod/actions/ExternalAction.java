package Thmod.actions;

import Thmod.Thmod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ExternalAction
        extends AbstractGameAction {

    private final int count;
    public AbstractPlayer p;

    public int draw;

    public ExternalAction(int draw, int count) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
        this.draw = draw;
        this.count = count;
    }

    public void update() {
        this.isDone = false;

        for (int i = 0; i < count; i++) {
            AbstractCard card = Thmod.getRandomCRNCard();
            AbstractDungeon.actionManager.addToBottom(
                    new MakeTempCardInDrawPileAction(card, 1, true, true)
            );
        }


        p.drawPile.shuffle();
        for (AbstractRelic r : p.relics) {
            r.onShuffle();
        }


        AbstractDungeon.actionManager.addToBottom(
                new DrawCardAction(p, draw)
        );


        this.isDone = true;
    }
}
