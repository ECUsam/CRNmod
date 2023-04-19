package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import Thmod.powers.Burning;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class RPG extends CustomCard {
    public static final String ID = "RPG";
    public static final String IMG_PATH = "img/cards/RPG.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int MagicNumber = 3;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final int ATTACK_DMG = 12 ;
    public RPG(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = ATTACK_DMG;
    }

    //TODO:BUG
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        //addToBot(new DamageAllEnemiesAction(player, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
            for(AbstractCreature m : AbstractDungeon.getMonsters().monsters){
                if(!m.isDead && !m.halfDead)addToBot(new DamageAction(m, new DamageInfo(m, this.damage), AbstractGameAction.AttackEffect.FIRE));
            }

        for(AbstractCreature m : AbstractDungeon.getMonsters().monsters) {
            if (!m.isDead && !m.isDying) {
                addToBot(new ApplyPowerAction(m, player, new Burning(m, MagicNumber)));
            }
        }
    }


    public AbstractCard makeCopy(){
        return new RPG();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
