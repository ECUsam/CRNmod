package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.DIYUSISupport;
import Thmod.powers.RMASupport;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TeamOfThree extends CustomCard {
    public static final String ID = "TeamOfThree";
    public static final String IMG_PATH = "img/cards/TeamOfThree.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int MagicNumber = 1;
    public TeamOfThree(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = MagicNumber;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new DIYUSISupport(player, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new RMASupport(player, 1), 1));
        addToBot(new GainEnergyAction(1));
    }

    public AbstractCard makeCopy(){
        return new TeamOfThree();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBaseCost(1);
        }
    }
}
