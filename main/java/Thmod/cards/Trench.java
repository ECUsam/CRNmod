package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Thmod.cards.ConfusingCombatants.countPowers;

public class Trench extends CustomCard
{
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Trench");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK_AMT = 8;
    private static final int UPGRADE_PLUS_BLOCK = 5;
    public static final String ID = "Trench";
    public static final String IMG_PATH = "img/cards/Trench.png";

    public Trench() {
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
        this.baseBlock = BLOCK_AMT;
        this.baseDamage = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.block+=countPowers()*3;
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }


    @Override
    public void applyPowers(){
        if(this.baseDamage<countPowers()){
            this.baseDamage+=countPowers();
            this.block+=countPowers()*2;
        }
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Trench();
    }




    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}