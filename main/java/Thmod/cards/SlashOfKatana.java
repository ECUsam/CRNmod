package Thmod.cards;

import Thmod.Thmod;
import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SlashOfKatana extends CustomCard {
    public static final String ID = "SlashOfKatana";
    public static final String IMG_PATH = "img/cards/SlashOfKatana.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 6;
    private static final int ATTACK_DMG = 8 ;
    private static AbstractCard lastAttack = null;

    public SlashOfKatana(){
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
        this.tags.add(CustomTags.SLAIN);
    }
    @Override
    public void applyPowers(){
        lastAttack = null;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            if (c.type == CardType.ATTACK) {
                lastAttack = c;
            }
        }
        if (lastAttack == null) {
            this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[2];
            initializeDescription();
        } else {
            this.rawDescription =
                    DESCRIPTION + EXTENDED_DESCRIPTION[0] + lastAttack.name + EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }


    @Override
    public void use(AbstractPlayer player, AbstractMonster monster) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(monster, new DamageInfo(player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        Thmod.logger.info("上一次攻击："+lastAttack);
        if (lastAttack == null) {
            addToBot(new DrawCardAction(player, 1, false));
            //AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
            //addToBot(new MakeTempCardInHandAction(card));
            }
        }


    @Override
    public AbstractCard makeCopy(){
        return new SlashOfKatana();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(4);
        }
    }

}
