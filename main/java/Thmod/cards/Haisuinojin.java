package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Haisuinojin extends CustomCard
{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Haisuinojin");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    public static final String ID = "Haisuinojin";
    public static final String IMG_PATH = "img/cards/Haisuinojin.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public Haisuinojin() {
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        //this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseBlock = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(Support.POWER_ID)){
            addToBot(new DrawCardAction(p, this.magicNumber, false));
        }else {
            addToBot(new DrawCardAction(p, this.block, false));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new Haisuinojin();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
