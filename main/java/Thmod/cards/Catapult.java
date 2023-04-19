package Thmod.cards;

import Thmod.Thmod;
import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Catapult extends CustomCard {
    public static final String ID = "Catapult";
    public static final String IMG_PATH = "img/cards/Catapult.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 0;
    private static final int MagicNumber = 3;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int ATTACK_DMG = 4 ;
    public Catapult(){
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
        player.getPower("Support").reducePower(1);
        for(int i=0; i<this.magicNumber;i++){
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        Thmod.logger.info("this.energyOnUse:"+ EnergyPanel.totalCount);
        if (p.hasPower("Support") && p.getPower("Support").amount >= 1 ) {
            return true;
        } else {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        }
    }


    public AbstractCard makeCopy(){
        return new Catapult();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
