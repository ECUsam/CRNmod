package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class gaba extends CustomCard {
    public static final String ID = "gaba";
    public static final String IMG_PATH = "img/cards/gaba.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int MagicNumber = 8;
    public gaba(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseBlock = this.block = 10;
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){

        RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
        int R = randomXS128.nextInt(this.block);

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, R));

    }


    public AbstractCard makeCopy(){
        return new gaba();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(4);
        }
    }
}
