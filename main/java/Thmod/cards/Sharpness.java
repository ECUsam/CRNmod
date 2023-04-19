package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.SIKSupport;
import Thmod.powers.SharpnessPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Sharpness extends CustomCard {
    public static final String ID = "Sharpness";
    public static final String IMG_PATH = "img/cards/Sharpness.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 1;
    public Sharpness(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new SharpnessPower(player), 1));
    }


    public AbstractCard makeCopy(){
        return new Sharpness();
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

