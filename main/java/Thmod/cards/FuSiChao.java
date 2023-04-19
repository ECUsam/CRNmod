package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.DIYUSISupport;
import Thmod.powers.KNNSupport;
import Thmod.powers.SIYUDISupport;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FuSiChao extends CustomCard {
    public static final String ID = "FuSiChao";
    public static final String IMG_PATH = "img/cards/FuSiChao.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 3;
    private static final int MagicNumber = 8;
    public FuSiChao(){
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
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        player.heal(this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new KNNSupport(player)));
    }


    public AbstractCard makeCopy(){
        return new FuSiChao();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBaseCost(2);
            upgradeMagicNumber(2);
        }
    }
}
