package Thmod.actions;

import Thmod.cards.Derivations.DontUse;
import Thmod.cards.Derivations.USE;
import Thmod.powers.DIYUSISupport;
import Thmod.powers.SIYUDISupport;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

import java.util.ArrayList;

import static Thmod.pathes.AbstractCardEnum.CRN_DERIVATIONS;

public class IfUseSupport extends AbstractGameAction {

    private final AbstractCard.CardColor cardColor = CRN_DERIVATIONS;
    public int magic;
    private boolean retrieveCard = false;
    public AbstractCard.CardType cardType = null;
    public AbstractCreature player = AbstractDungeon.player;

    public IfUseSupport(int magic){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = 1;
        this.magic = magic;
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
                    if(disCard instanceof USE){
                        player.getPower("Support").reducePower(this.magic);
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new SIYUDISupport(player, 1), 1));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new DIYUSISupport(player, 1), 1));
                    }else if(disCard instanceof DontUse){
                        AbstractDungeon.actionManager.addToBottom(
                                new ChoiceSisterAction());
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
        derp.add(new USE());
        derp.add(new DontUse());

        return derp;
    }
}
