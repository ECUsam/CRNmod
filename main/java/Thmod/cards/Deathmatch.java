package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Deathmatch extends CustomCard {
    public static final String ID = "Deathmatch";
    public static final String IMG_PATH = "img/cards/Deathmatch.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 2;
    private static final int MagicNumber = 1;
    private static final int ATTACK_DMG = 4 ;
    public Deathmatch(){
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
        this.baseDamage = ATTACK_DMG;
        this.exhaust = true;
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        addToBot(new SkipEnemiesTurnAction());
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("DIYUSISupport") && p.hasPower("SIYUDISupport") ) {
            return true;
        } else {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        }
    }

    public AbstractCard makeCopy(){
        return new Deathmatch();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
