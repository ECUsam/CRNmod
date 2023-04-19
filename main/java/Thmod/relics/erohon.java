package Thmod.relics;

import Thmod.Thmod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class erohon extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "erohon";
    private static final String IMG = "img/relics/erohon.png";
    private static final String IMG_OTL = "img/relics/outline/erohon.png";

    public erohon() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.UNCOMMON,
                LandingSound.FLAT
        );
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new erohon();
    }


}
