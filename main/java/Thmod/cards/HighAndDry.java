package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HighAndDry extends CustomCard {
    public static final String ID = "HighAndDry";
    public static final String IMG_PATH = "img/cards/HighAndDry.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 1;
    private static final int BLOCK = 8;
    private static final int UPGRADE_PLUS_BL = 4;
    public HighAndDry(){
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
        this.baseBlock = BLOCK;
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.block));
        if(!player.hasPower(Support.POWER_ID) || player.getPower("Support").amount <=3){
            AbstractDungeon.actionManager.addToBottom(
                    new DrawCardAction(player, this.magicNumber)
            );
            addToBot(new GainEnergyAction(1));
        }
    }


    public AbstractCard makeCopy(){
        return new HighAndDry();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BL);
        }
    }
}
