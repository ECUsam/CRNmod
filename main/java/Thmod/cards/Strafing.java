package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strafing extends CustomCard {
    public static final String ID = "Strafing";
    public static final String IMG_PATH = "img/cards/Strafing.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int MagicNumber = 6;
    private static final int ATTACK_DMG = 3 ;
    public Strafing(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.COMMON,
                CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = this.damage = ATTACK_DMG;
        this.tags.add(CustomTags.SHOTS);
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        for(int i =1;i<=MagicNumber;i++){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }

    }


    public AbstractCard makeCopy(){
        return new Strafing();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
                upgradeMagicNumber(2);
        }
    }
}
