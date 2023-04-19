package Thmod.cards.Derivations;

import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CyclingByBike extends CustomCard {

    public static final String ID = "CyclingByBike";
    public static final String IMG_PATH = "img/cards/CyclingByBike.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;

    public CyclingByBike(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.STATUS,
                AbstractCardEnum.CRN_DERIVATIONS,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.isEthereal = true;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {

    }
    public void onChoseThisOption() {}

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
    @Override
    public AbstractCard makeCopy(){
        return new CyclingByBike();
    }
}
