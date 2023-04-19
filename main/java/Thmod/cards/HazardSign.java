package Thmod.cards;

import Thmod.actions.ConvergingAction;
import Thmod.actions.HazardSignAction;
import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HazardSign extends CustomCard {
    public static final String ID = "HazardSign";
    public static final String IMG_PATH = "img/cards/HazardSign.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 3;
    private static final int BLOCK = 12;
    private static final int MagicNumber = 1;

    public HazardSign(){
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
        this.baseBlock = BLOCK;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        player.getPower("SIYUDISupport").reducePower(1);
        AbstractDungeon.actionManager.addToBottom(new HazardSignAction());
        if(this.magicNumber == 2){
            AbstractDungeon.actionManager.addToBottom(new HazardSignAction());
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("SIYUDISupport") && p.getPower("SIYUDISupport").amount >= 1 ) {
            return true;
        } else {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        }
    }

    public AbstractCard makeCopy(){
        return new HazardSign();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

}