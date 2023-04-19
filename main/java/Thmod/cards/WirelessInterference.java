package Thmod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.Support;

public class WirelessInterference extends CustomCard {

    public static final String ID = "WirelessInterference";
    public static final String IMG_PATH = "img/cards/WirelessInterference.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int DEBUFFNUM = 1;
    private static final int UPDATENUM = 1;
    private static final int STACK = 2;
    private static final int Amount =2;

    public WirelessInterference(){
        super(
             ID,
             NAME,
             IMG_PATH,
             COST,
             DESCRIPTION,
             CardType.SKILL,
             AbstractCardEnum.CRN_COLOR,
             CardRarity.BASIC,
             CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = DEBUFFNUM;
    }
    public void use(AbstractPlayer player, AbstractMonster monster){
        addToBot(new ApplyPowerAction(monster, player, new WeakPower(monster, magicNumber, false)));
        addToBot(new ApplyPowerAction(monster, player, new VulnerablePower(monster, magicNumber, false)));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                new Support(player, Amount), STACK));
    }

    public AbstractCard makeCopy(){
        return new WirelessInterference();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeMagicNumber(UPDATENUM);
        }
    }


}
