package Thmod.cards.Curses;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.SetDontTriggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PoorSignal extends CustomCard {

    public static final String ID = "PoorSignal";
    public static final String IMG_PATH = "img/cards/curse/PoorSignal.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;

    public PoorSignal(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.STATUS,
                AbstractCard.CardColor.CURSE,
                CardRarity.CURSE,
                CardTarget.NONE
        );
        //this.isEthereal = true;
        //this.exhaust = true;
    }

    @Override
    public void upgrade() {}

    public void triggerWhenDrawn(){
        addToBot(new SetDontTriggerAction(this, false));
    }


    public void triggerOnEndOfTurnForPlayingCard(){
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }

    @Override
    public void use(AbstractPlayer Player, AbstractMonster Monster) {
        if(this.dontTriggerOnUseCard){
            if(Player.hasPower("Support")){
                Player.getPower("Support").reducePower(1);
            }
        }
    }
    @Override
    public AbstractCard makeCopy(){
        return new PoorSignal();
    }
}
