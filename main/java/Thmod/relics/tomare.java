package Thmod.relics;

import Thmod.Thmod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class tomare extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "tomare";
    private static final String IMG = "img/relics/tomare.png";
    private static final String IMG_OTL = "img/relics/outline/tomare.png";

    public tomare() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.RARE,
                LandingSound.HEAVY
        );
    }

    public void atBattleStart() {
        addToBot(new SkipEnemiesTurnAction());
        this.counter = 0;
    }

    @Override
    public void atTurnStart() {
        this.counter++;
        if(this.counter == 2){
            this.grayscale = true;
        }
    }

    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new tomare();
    }


}
