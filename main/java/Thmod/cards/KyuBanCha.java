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
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.BloodPotion;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;

public class KyuBanCha extends CustomCard {
    public static final String ID = "KyuBanCha";
    public static final String IMG_PATH = "img/cards/KyuBanCha.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int MagicNumber = 4;
    private static final int BLOCK = 12;
    public KyuBanCha(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.exhaust = true;
        this.baseBlock = BLOCK;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.block));
        //addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
        AbstractPotion potion = AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.COMMON, true);
        if(potion.equals(new BloodPotion())){
            potion = AbstractDungeon.returnRandomPotion(AbstractPotion.PotionRarity.COMMON, true);
        }
        AbstractDungeon.effectsQueue.add(new ObtainPotionEffect(potion));
    }


    public AbstractCard makeCopy(){
        return new KyuBanCha();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeBlock(6);
        }
    }
}
