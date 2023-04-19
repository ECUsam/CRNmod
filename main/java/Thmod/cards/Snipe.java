package Thmod.cards;

import Thmod.Thmod;
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
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Snipe extends CustomCard {
    public static final String ID = "Snipe";
    public static final String IMG_PATH = "img/cards/Snipe.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 2;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int ATTACK_DMG = 18 ;
    public Snipe(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.RARE,
                CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = ATTACK_DMG;
        this.tags.add(CustomTags.SHOTS);
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        player.getPower("Support").reducePower(this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(monster,
                        new DamageInfo(player,
                                this.damage,
                                this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        Thmod.logger.info("this.energyOnUse:"+ EnergyPanel.totalCount);
        if (p.hasPower("Support") && p.getPower("Support").amount >= this.magicNumber ) {
            return true;
        } else {
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];
            return false;
        }
    }


    public AbstractCard makeCopy(){
        return new Snipe();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(-1);
        }
    }
}