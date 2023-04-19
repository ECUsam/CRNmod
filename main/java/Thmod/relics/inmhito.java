package Thmod.relics;
import Thmod.Thmod;
import Thmod.actions.RandomDebuff;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class inmhito extends CustomRelic{
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "inmhito";
    private static final String IMG = "img/relics/inmhito.png";
    private static final String IMG_OTL = "img/relics/outline/inmhito.png";
    private static final int Magicnum = 1;

    public inmhito() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.STARTER,
                LandingSound.HEAVY
        );
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target){
        if(target.isPlayer){
            return;
        }
        if(info.owner.equals(player)) {
            logger.info("=========开始发动淫梦以太刀效果==========");
            flash();
            if (damageAmount > 0 && info.type == NORMAL)
                addToBot(new RandomDebuff(target, Magicnum));
            logger.info("============发动完成===========");
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new inmhito();
    }


}
