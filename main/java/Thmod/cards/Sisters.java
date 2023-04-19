package Thmod.cards;

import Thmod.actions.ChoiceSisterAction;
import Thmod.actions.IfUseSupport;
import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sisters extends CustomCard {
    public static final String ID = "Sisters";
    public static final String IMG_PATH = "img/cards/Sisters.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 3;
    public Sisters(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        if(!player.hasPower("Support")){
            AbstractDungeon.actionManager.addToBottom(
                    new ChoiceSisterAction());
        }else {
            if(player.getPower("Support").amount <this.magicNumber){
                AbstractDungeon.actionManager.addToBottom(
                        new ChoiceSisterAction());
            } else if (player.getPower("Support").amount>=this.magicNumber) {
                AbstractDungeon.actionManager.addToBottom(
                        new IfUseSupport(this.magicNumber));
            }
        }
            
        
    }


    public AbstractCard makeCopy(){
        return new Sisters();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(-2);
        }
    }
}
