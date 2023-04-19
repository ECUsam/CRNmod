package Thmod.relics;

import Thmod.Thmod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Turioba extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "Turioba";
    private static final String IMG = "img/relics/Turioba.png";
    private static final String IMG_OTL = "img/relics/outline/Turioba.png";

    public Turioba() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.SHOP,
                LandingSound.FLAT
        );
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if ((card.costForTurn == 1)) {
            this.counter += 1;
            if (this.counter >= 5) {
                this.counter = 0;
                flash();
                AbstractDungeon.actionManager.addToBottom(
                        new RelicAboveCreatureAction(AbstractDungeon.player, this)
                );
                AbstractDungeon.actionManager.addToBottom(
                        new DrawCardAction(AbstractDungeon.player, 1)
                );
            }
        }
    }

    public void onVictory() {
        this.counter = -1;
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new Turioba();
    }


}
