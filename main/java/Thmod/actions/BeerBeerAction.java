package Thmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BeerBeerAction extends AbstractGameAction {

    private final AbstractPlayer player = AbstractDungeon.player;

    @Override
    public void update() {
        findAndModifyCard();
        tickDuration();
    }

    private void findAndModifyCard(){

    }
}
