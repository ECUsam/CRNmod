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

public class RecallAction extends AbstractGameAction {

    private final AbstractCard.CardColor cardColor = CRN_DERIVATIONS;
    private boolean retrieveCard = false;
    public AbstractCard.CardType cardType = null;
    public AbstractCreature player = AbstractDungeon.player;

    public RecallAction(){
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
                    if(disCard instanceof DIYUSI_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new DIYUSISupport(player, 1), 1));
                    }else if(disCard instanceof SIYUDI_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new SIYUDISupport(player, 1), 1));
                    } else if (disCard instanceof HNS_DS) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new HNSSupport(player, 1), 1));
                    }else if(disCard instanceof RMA_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new RMASupport(player, 1), 1));
                    }else if(disCard instanceof MDCN_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new MDCNSupport(player, 1), 1));
                    }else if(disCard instanceof SZ_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new SZPower(player, 1), 1));
                    }else if(disCard instanceof SIK_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new SIKSupport(player, 1), 1));
                    }else if(disCard instanceof ICG_DS){
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new ICGSupport(player, 1), 1));
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
        int number = 8;
        ArrayList<AbstractCard> tmp = new ArrayList<>();
        ArrayList<AbstractCard> derp = new ArrayList<>();
        tmp.add(new DIYUSI_DS());
        tmp.add(new SIYUDI_DS());
        tmp.add(new HNS_DS());
        tmp.add(new RMA_DS());
        tmp.add(new MDCN_DS());
        tmp.add(new SZ_DS());
        tmp.add(new SIK_DS());
        tmp.add(new ICG_DS());



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
//TODO:卧槽选择界面没搞明白（池沼）
