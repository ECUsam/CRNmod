package Thmod.relics;

import Thmod.Thmod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Biim extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "Biim";
    private static final String IMG = "img/relics/Biim.png";
    private static final String IMG_OTL = "img/relics/outline/Biim.png";


    public Biim() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.UNCOMMON,
                LandingSound.FLAT
        );
    }

    public void atBattleStart() {
        this.counter = 0;
    }

    public void atTurnStart() {
        flash();
        flashcount();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.counter));
    }

    public void flashcount(){
        int count = 0;
        for(AbstractPower ignored : player.powers){
            count++;
        }
        this.counter = count;
    }

    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        flashcount();
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
        return new Biim();
    }


}
