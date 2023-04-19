package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;


public class LegShot extends CustomCard {
    public static final String ID = "LegShot";
    public static final String IMG_PATH = "img/cards/LegShot.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int MagicNumber = 1;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int ATTACK_DMG = 4 ;
    public LegShot(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.BASIC,
                CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = ATTACK_DMG;
        this.tags.add(CustomTags.SHOTS);
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(monster,
                        new DamageInfo(player,
                                this.damage,
                                this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (monster != null &&(monster.intent != AbstractMonster.Intent.ATTACK)){
            addToBot(new ApplyPowerAction(monster, player, new VulnerablePower(monster, MagicNumber, false)));
        }

    }


    public AbstractCard makeCopy(){
        return new LegShot();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
