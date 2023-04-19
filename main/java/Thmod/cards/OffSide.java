package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class OffSide  extends CustomCard
{
    //从.json文件中提取键名为Strike_CRN的信息
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("OffSide");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int Magic = 2;
    public static final String ID = "OffSide";
    public static final String IMG_PATH = "img/cards/OffSide.png";

    public OffSide() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.CRN_COLOR, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = Magic;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber, false));
        int num;
        if(p.hasPower(Support.POWER_ID)){
            num = p.getPower(Support.POWER_ID).amount;
            player.getPower(Support.POWER_ID).reducePower(num);
            addToBot(new RemoveSpecificPowerAction(p, p, Support.POWER_ID));
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, num), num));
            addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, num), num));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new OffSide();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
