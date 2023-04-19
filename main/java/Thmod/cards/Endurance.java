package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Endurance extends CustomCard
{
    //从.json文件中提取键名为Strike_CRN的信息
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Endurance");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public static final String ID = "Endurance";
    public static final String IMG_PATH = "img/cards/Endurance.png";

    public Endurance() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.CRN_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(this.baseMagicNumber));
        addToBot(new ApplyPowerAction(p, p, new Support(p, this.baseMagicNumber), this.baseMagicNumber));
        this.baseMagicNumber++;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Endurance();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            upgradeName();
        upgradeBaseCost(0);
        }
    }
}
