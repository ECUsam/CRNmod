package Thmod.actions;

import Thmod.powers.BloodPower;
import Thmod.powers.Support;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Thmod.pathes.CustomTags.SHOTS;
import static Thmod.pathes.CustomTags.SLAIN;

public class PostureOfBattleAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");

    public static final String[] TEXT = uiStrings.TEXT;

    public AbstractPlayer p;

    public boolean isRandom;

    public boolean anyNumber;

    public boolean canPickZero;

    public static int numExhausted;

    public int num;

    public PostureOfBattleAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, int num) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.EXHAUST;
        this.num = num;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            if (!this.anyNumber && this.p.hand
                    .size() <= this.amount) {
                this.amount = this.p.hand.size();
                numExhausted = this.amount;
                int tmp = this.p.hand.size();
                for (int i1 = 0; i1 < tmp; i1++) {
                    AbstractCard c = this.p.hand.getTopCard();
                    cardEX(c);
                    this.p.hand.moveToDiscardPile(c);
                }
                CardCrawlGame.dungeon.checkForPactAchievement();
                return;
            }
            if (this.isRandom) {
                for (int i = 0; i < this.amount; i++)
                    this.p.hand.moveToDiscardPile(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
                CardCrawlGame.dungeon.checkForPactAchievement();
            } else {
                numExhausted = this.amount;
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                cardEX(c);
                this.p.hand.moveToDiscardPile(c);
            }
            CardCrawlGame.dungeon.checkForPactAchievement();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
    }

    public void cardEX(AbstractCard c) {
        if (c.hasTag(SHOTS))
            addToBot(new ApplyPowerAction(this.p, this.p, new Support(this.p, this.num)));
        if (c.hasTag(SLAIN))
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!m.isDead && !m.isDying)
                    addToBot(new ApplyPowerAction(m, this.p, new BloodPower(m, this.num)));
            }
    }
}