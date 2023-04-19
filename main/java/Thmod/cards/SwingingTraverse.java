package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SwingingTraverse extends CustomCard {
    public static final String ID = "SwingingTraverse";
    public static final String IMG_PATH = "img/cards/SwingingTraverse.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int MagicNumber = 1;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int ATTACK_DMG = 3 ;
    public SwingingTraverse(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.COMMON,
                CardTarget.ALL_ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = ATTACK_DMG;
        this.tags.add(CustomTags.SHOTS);
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        addToBot(new DamageAllEnemiesAction(player, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot(new DamageAllEnemiesAction(player, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

    }


    public AbstractCard makeCopy(){
        return new SwingingTraverse();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
