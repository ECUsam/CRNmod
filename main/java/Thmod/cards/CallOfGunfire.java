package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.CallPfGunfirePower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class CallOfGunfire extends CustomCard {
    public static final String ID = "CallOfGunfire";
    public static final String IMG_PATH = "img/cards/CallOfGunfire.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 2;
    private static final int ATTACK_DMG = 5 ;
    public CallOfGunfire(){
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
        this.baseDamage = this.damage = ATTACK_DMG;
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new CallPfGunfirePower(player, 1,this.magicNumber), 1));

    }


    public AbstractCard makeCopy(){
        return new CallOfGunfire();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(2);
        }
    }
}
