package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.MilkPower;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Milke extends CustomCard {
    public static final String ID = "Milke";
    public static final String IMG_PATH = "img/cards/Milke.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 2;
    public Milke(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new MilkPower(player)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new Support(player, this.magicNumber), this.magicNumber));
    }


    public AbstractCard makeCopy(){
        return new Milke();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}
