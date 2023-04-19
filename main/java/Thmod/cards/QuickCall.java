package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.QuestPower;
import Thmod.powers.QuickCallPower;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class QuickCall extends CustomCard {
    public static final String ID = "QuickCall";
    public static final String IMG_PATH = "img/cards/QuickCall.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int MagicNumber = 3;

    public QuickCall(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        addToBot(new ApplyPowerAction(player, player, new Support(player, this.magicNumber)));
        addToBot(new ApplyPowerAction(player, player, new Support(player, 1)));
        if(!player.hasPower(QuickCallPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(player, player, new QuickCallPower(player, 2), 2));
        }else {
            addToBot(new ApplyPowerAction(player, player, new QuickCallPower(player, 1), 1));
        }
    }


    public AbstractCard makeCopy(){
        return new QuickCall();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
