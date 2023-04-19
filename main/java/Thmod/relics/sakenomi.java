package Thmod.relics;

import Thmod.Thmod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class sakenomi extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "sakenomi";
    private static final String IMG = "img/relics/sakenomi.png";
    private static final String IMG_OTL = "img/relics/outline/sakenomi.png";
    private static final int Magicnum = 1;

    public sakenomi() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.UNCOMMON,
                LandingSound.FLAT
        );
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if(!m.hasPower(MinionPower.POWER_ID)){
            addToBot(new HealAction(player, player, Magicnum));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new sakenomi();
    }


}
