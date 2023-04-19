package Thmod.cards;

import Thmod.Thmod;
import Thmod.actions.RecallAction;
import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class RandomCall extends CustomCard {
    public static final String ID = "RandomCall";
    public static final String IMG_PATH = "img/cards/RandomCall.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 0;
    //TODO:3
    private static final int MagicNumber = 3;
    public RandomCall(){
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
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(
                new RecallAction());
        player.getPower(Support.POWER_ID).reducePower(this.magicNumber);
    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        Thmod.logger.info("this.energyOnUse:"+ EnergyPanel.totalCount);
        if (p.hasPower("Support") && p.getPower("Support").amount >= this.magicNumber ) {
            return true;
        } else {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        }
    }


    public AbstractCard makeCopy(){
        return new RandomCall();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(-1);
        }
    }
}
