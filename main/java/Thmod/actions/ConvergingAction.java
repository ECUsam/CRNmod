package Thmod.actions;

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

public class ConvergingAction extends AbstractGameAction {

    private final AbstractCard.CardColor cardColor = CRN_DERIVATIONS;
    private boolean retrieveCard = false;
    private AbstractCard.CardType cardType = null;
    public AbstractCreature player = AbstractDungeon.player;

    public ConvergingAction(){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 1;
    }


    @Override
    public void update() {
        ArrayList<AbstractCard> generatedCards = generateSupportChoices(cardColor);
        if(this.duration == Settings.ACTION_DUR_FAST){
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], (this.cardType != null));
            tickDuration();
        } else {
            if(!this.retrieveCard){
                if(AbstractDungeon.cardRewardScreen.discoveryCard != null){
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if(disCard instanceof KingdomOfWalkers){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new KingdomOfWalkersPower(player, 1), 1));
                    }else if(disCard instanceof CyclingByBike){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new CyclingByBikePower(player, 1), 1));
                    } else if (disCard instanceof MotorVehicleAttention) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new MotorVehicleAttentionPower(player, 2), 2));
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
        derp.add(new KingdomOfWalkers());
        derp.add(new CyclingByBike());
        derp.add(new MotorVehicleAttention());
        return derp;
    }
}
