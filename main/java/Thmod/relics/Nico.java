package Thmod.relics;

import Thmod.Thmod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;


public class Nico extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "Nico";
    private static final String IMG = "img/relics/Nico.png";
    private static final String IMG_OTL = "img/relics/outline/Nico.png";

    public Nico() {
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
            for(AbstractCreature m : AbstractDungeon.getMonsters().monsters){
                addToBot(new DamageAction(m, new DamageInfo(m, 10), AbstractGameAction.AttackEffect.FIRE));
            }
            this.firstTurn = false;
        }
    }



    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new Nico();
    }


}
