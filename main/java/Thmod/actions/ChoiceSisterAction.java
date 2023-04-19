package Thmod.actions;

import Thmod.Thmod;
import Thmod.cards.Derivations.*;
import Thmod.powers.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

import java.util.ArrayList;

import static Thmod.pathes.AbstractCardEnum.CRN_DERIVATIONS;

public class ChoiceSisterAction  extends AbstractGameAction {

    private final AbstractCard.CardColor cardColor = CRN_DERIVATIONS;
    private boolean retrieveCard = false;
    public AbstractCard.CardType cardType = null;
    public AbstractCreature player = AbstractDungeon.player;

    public ChoiceSisterAction(){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 1;
    }


    @Override
    public void update() {
        ArrayList<AbstractCard> generatedCards = generateSupportChoices(cardColor);
        if(this.duration == Settings.ACTION_DUR_FAST){
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], (this.cardType != null));
            Thmod.logger.info("duration:"+this.duration);
            tickDuration();
        } else {
            Thmod.logger.info("retrieveCard:"+this.retrieveCard);
            if(!this.retrieveCard){
                Thmod.logger.info("进入retrieveCard:");
                Thmod.logger.info("discoveryCard:"+AbstractDungeon.cardRewardScreen.discoveryCard);
                if(AbstractDungeon.cardRewardScreen.discoveryCard != null){

                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    Thmod.logger.info("disCard:"+disCard);
                    if(disCard instanceof DIYUSI_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new DIYUSISupport(player, 1), 1));
                    }else if(disCard instanceof SIYUDI_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new SIYUDISupport(player, 1), 1));
                    }
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            tickDuration();
            this.isDone = true;
        }

    }

    private ArrayList<AbstractCard> generateSupportChoices(AbstractCard.CardColor cardColor){
        ArrayList<AbstractCard> derp = new ArrayList<>();
        derp.add(new DIYUSI_DS());
        derp.add(new SIYUDI_DS());

        return derp;
    }
}
