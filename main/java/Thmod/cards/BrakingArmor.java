package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.BrakingArmorPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BrakingArmor extends CustomCard {
    public static final String ID = "BrakingArmor";
    public static final String IMG_PATH = "img/cards/BrakingArmor.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int MagicNumber = 0;
    private static final int BLOCL = 8;
    public BrakingArmor(){
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
        this.baseBlock = BLOCL;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new BrakingArmorPower(player, 1), 1));
        if(this.magicNumber == 1){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.block));
        }
    }


    public AbstractCard makeCopy(){
        return new BrakingArmor();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
