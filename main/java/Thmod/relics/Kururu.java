package Thmod.relics;

import Thmod.Thmod;
import Thmod.actions.RecallAction;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Kururu extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "Kururu";
    private static final String IMG = "img/relics/Kururu.png";
    private static final String IMG_OTL = "img/relics/outline/Kururu.png";

    public Kururu() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.COMMON,
                LandingSound.HEAVY
        );
    }
    private boolean firstTurn = true;

    public void atPreBattle(){
        this.firstTurn = true;
    }

    public void atTurnStart() {
        if(this.firstTurn){
            flash();
            AbstractDungeon.actionManager.addToBottom(
                    new RecallAction());
            this.firstTurn = false;
        }
    }



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new Kururu();
    }
}
