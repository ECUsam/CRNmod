package Thmod.cards;

import Thmod.actions.ExternalAction;
import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExternalAssistance extends CustomCard {

    public static final String ID = "ExternalAssistance";
    public static final String IMG_PATH = "img/cards/ExternalAssistance.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int DRAW = 2;
    private static final int UPG_DRAW = 1;

    public ExternalAssistance() {
        super(
                ID, NAME, IMG_PATH,
                COST, DESCRIPTION,
                AbstractCard.CardType.SKILL,
                AbstractCardEnum.CRN_COLOR,
                AbstractCard.CardRarity.UNCOMMON,
                AbstractCard.CardTarget.SELF
        );
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.baseDamage= 1;
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.player.hasPower("Support")) {
            this.baseDamage = 1 + AbstractDungeon.player.getPower("Support").amount / 2;
        }else {
            this.baseDamage = 1;
        }
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ExternalAction(this.magicNumber, this.baseDamage)
        );
    }

    public AbstractCard makeCopy() {
        return new ExternalAssistance();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_DRAW);
        }
    }

}
