package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.SeekPower;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Reforming extends CustomCard {
    public static final String ID = "Reforming";
    public static final String IMG_PATH = "img/cards/Reforming.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int BLOCK = 12;
    private static final int UPDA_BLOCK = 6;
    private static final int COST = 2;
    private static final int MagicNumber = 1;

    public Reforming(){
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
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new Support(player, 1), 1));
    }


    public AbstractCard makeCopy(){
        return new Reforming();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(UPDA_BLOCK);
            //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            //initializeDescription();
        }
    }
}
