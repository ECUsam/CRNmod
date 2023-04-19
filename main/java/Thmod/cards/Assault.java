package Thmod.cards;

import Thmod.Thmod;
import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Assault extends CustomCard {
    public static final String ID = "Assault";
    public static final String IMG_PATH = "img/cards/Assault.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private boolean Suppo = false;
    private static final int COST = 1;
    private static final int MagicNumber = 1;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int ATTACK_DMG = 4;
    public Assault(){
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
        this.tags.add(CustomTags.SLAIN);
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        addToBot(new ExhaustAction(1, false));
        if(AbstractDungeon.player.hasPower("Support")){
            this.baseDamage += AbstractDungeon.player.getPower("Support").amount;
            this.Suppo = true;
        }
        Thmod.logger.info("Basedamage:"+this.baseDamage);
        //addToBot(new DamageAllEnemiesAction(player, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        for(AbstractCreature m : AbstractDungeon.getMonsters().monsters){
            if(!m.isDead && !m.halfDead)addToBot(new DamageAction(m, new DamageInfo(m, this.damage), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
        if(Suppo){
            this.baseDamage -= AbstractDungeon.player.getPower("Support").amount;
            this.Suppo = false;
        }
    }



    public AbstractCard makeCopy(){
        return new Assault();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
