package Thmod.actions;

import Thmod.cards.Derivations.*;
import Thmod.powers.*;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;

import java.util.ArrayList;

import static Thmod.pathes.AbstractCardEnum.CRN_DERIVATIONS;

public class HazardSignAction extends AbstractGameAction {

    private final AbstractCard.CardColor cardColor = CRN_DERIVATIONS;
    private boolean retrieveCard = false;
    public AbstractCard.CardType cardType = null;
    public AbstractCreature player = AbstractDungeon.player;

    public HazardSignAction(){
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
                    if(disCard instanceof Lightning){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new LightningPower(player,1),1));
                    }else if(disCard instanceof Fire_SI){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new FirePower(player)));
                    } else if (disCard instanceof RadioactiveSubstance) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new CorrosivityPower(player)));
                    }else if(disCard instanceof AttentionToFallingObjects){
                        AbstractDungeon.actionManager.addToBottom(
                                new AttentionToFallingObjectsAction(30));
                    }else if(disCard instanceof Corrosivity){
                        AbstractDungeon.actionManager.addToBottom(
                                new CorrosivityActualAction());
                    }else if(disCard instanceof Laser){
                        AbstractDungeon.actionManager.addToBottom(
                                new LaserAction());
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
        int number = 6;
        ArrayList<AbstractCard> tmp = new ArrayList<>();
        ArrayList<AbstractCard> derp = new ArrayList<>();
        tmp.add(new Laser());
        tmp.add(new Lightning());
        tmp.add(new Corrosivity());
        tmp.add(new Fire_SI());
        tmp.add(new RadioactiveSubstance());
        tmp.add(new AttentionToFallingObjects());


        RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
        int R1 = randomXS128.nextInt(number);
        int R2 = randomXS128.nextInt(number);
        while (R1 == R2){
            R2 = randomXS128.nextInt(number);
        }
        int R3 = randomXS128.nextInt(number);
        while (R3 == R1 || R3 == R2) {
            R3 = randomXS128.nextInt(number);
        }

        derp.add(tmp.get(R1).makeCopy());
        derp.add(tmp.get(R2).makeCopy());
        derp.add(tmp.get(R3).makeCopy());
        return derp;
    }
}
