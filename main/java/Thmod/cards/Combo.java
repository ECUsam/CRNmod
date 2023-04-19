package Thmod.cards;

import Thmod.Thmod;
import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Thmod.pathes.CustomTags.SHOTS;
import static Thmod.pathes.CustomTags.SLAIN;

public class Combo extends CustomCard {
    public static final String ID = "Combo";
    public static final String IMG_PATH = "img/cards/Combo.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 6;
    private static final int ATTACK_DMG = 5 ;
    public static AbstractCard lastAttack = null;
    public Combo(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = this.damage = ATTACK_DMG;
        this.tags.add(CustomTags.SHOTS);
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(monster,
                        new DamageInfo(player,
                                this.damage,
                                this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_VERTICAL));

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(monster,
                        new DamageInfo(player,
                                this.damage,
                                this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.BLUNT_LIGHT));


        lastAttack = null;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.type == CardType.ATTACK && c!=this) {
                lastAttack = c;
            }
        }
        if(lastAttack != null) {
            Thmod.logger.info("lastcard:" + lastAttack);
            if (lastAttack.hasTag(SLAIN)) {
                AbstractDungeon.actionManager.addToBottom(
                        new DrawCardAction(player, 1)
                );
            }
            if (lastAttack.hasTag(SHOTS)) {
                addToBot(new GainEnergyAction(1));
            }
        }
    }


    public AbstractCard makeCopy(){
        return new Combo();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(3);
        }
    }
}
