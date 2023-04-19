package Thmod.relics;

import Thmod.Thmod;
import Thmod.powers.SIYUDISupport;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class CiXing extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "CiXing";
    private static final String IMG = "img/relics/CiXing.png";
    private static final String IMG_OTL = "img/relics/outline/CiXing.png";

    public CiXing() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.COMMON,
                LandingSound.SOLID
        );
    }

    public void atTurnStart() {
        if(player.hasPower(SIYUDISupport.POWER_ID)){
            addToBot(new GainEnergyAction(1));
        }
    }



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new CiXing();
    }


}
