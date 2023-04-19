package Thmod.relics;

import Thmod.Thmod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Zailiaoya extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "Zailiaoya";
    private static final String IMG = "img/relics/Zailiaoya.png";
    private static final String IMG_OTL = "img/relics/outline/Zailiaoya.png";

    public Zailiaoya() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.COMMON,
                LandingSound.FLAT
        );
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        this.counter++;

        if (this.counter == 2) {
            flash();
            addToBot(new RelicAboveCreatureAction(player, this));
            addToBot(new DrawCardAction(player, 1, false));
        }
        if(this.counter == 3){
            flash();
            addToBot(new RelicAboveCreatureAction(player, this));
            addToBot(new DrawCardAction(player, 1, false));
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
        return new Zailiaoya();
    }


}
