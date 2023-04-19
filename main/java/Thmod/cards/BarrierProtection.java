package Thmod.cards;

import Thmod.Thmod;
import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class BarrierProtection extends CustomCard {
    public static final String ID = "BarrierProtection";
    public static final String IMG_PATH = "img/cards/BarrierProtection.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 2;
    private static final int UPGRADE_PLUS_BLK = 4;
    private static final int BLOCK = 14 ;
    public BarrierProtection(){
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
        player.getPower("Support").reducePower(this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.block));

    }
    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        Thmod.logger.info("this.energyOnUse:"+ EnergyPanel.totalCount);
        if (p.hasPower("Support") && p.getPower("Support").amount >= this.magicNumber ) {
            return true;
        } else {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        }
    }


    public AbstractCard makeCopy(){
        return new BarrierProtection();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLK);
        }
    }
}