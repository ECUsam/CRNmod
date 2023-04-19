package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.FuryCutterPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FuryCutter extends CustomCard {
    public static final String ID = "FuryCutter";
    public static final String IMG_PATH = "img/cards/FuryCutter.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int MagicNumber = 8;
    private static final int UPGRADE_PLUS_MN = 3;
    public FuryCutter(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.COMMON,
                CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
    addToBot(new ApplyPowerAction(monster, player, new FuryCutterPower(monster, 1,this.magicNumber), 1));

    }


    public AbstractCard makeCopy(){
        return new FuryCutter();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MN);
        }
    }
}
