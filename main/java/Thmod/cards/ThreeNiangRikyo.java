package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ThreeNiangRikyo extends CustomCard {
    public static final String ID = "ThreeNiangRikyo";
    public static final String IMG_PATH = "img/cards/ThreeNiangRikyo.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 3;
    public ThreeNiangRikyo(){
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

    //TODO:检查堆栈功能
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        for(int i=0;i<MagicNumber;i++) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                    new Support(player, 1), 1));
        }

    }


    public AbstractCard makeCopy(){
        return new ThreeNiangRikyo();
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