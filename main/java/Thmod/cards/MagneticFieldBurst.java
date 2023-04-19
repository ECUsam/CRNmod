package Thmod.cards;

import Thmod.cards.Curses.PoorSignal;
import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagneticFieldBurst extends CustomCard {
    public static final String ID = "MagneticFieldBurst";
    public static final String IMG_PATH = "img/cards/MagneticFieldBurst.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 3;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final int ATTACK_DMG = 10;
    public MagneticFieldBurst(){
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
        this.baseDamage = this.damage = ATTACK_DMG;
    }


    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        //addToBot(new DamageAllEnemiesAction(player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.LIGHTNING));
        for(AbstractCreature m : AbstractDungeon.getMonsters().monsters){
            if(!m.isDead && !m.halfDead)addToBot(new DamageAction(m, new DamageInfo(m, this.damage), AbstractGameAction.AttackEffect.LIGHTNING));
        }
        AbstractDungeon.actionManager.addToBottom(
                new MakeTempCardInHandAction(new PoorSignal(), 1)
        );
    }


    public AbstractCard makeCopy(){
        return new MagneticFieldBurst();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
