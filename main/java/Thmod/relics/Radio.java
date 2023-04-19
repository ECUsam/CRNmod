package Thmod.relics;

import Thmod.Thmod;
import Thmod.actions.RandomDebuff;
import Thmod.actions.RandompowerAction;
import Thmod.actions.RecallAction;
import Thmod.cards.RandomCall;
import Thmod.powers.Support;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.NORMAL;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Radio extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "Radio";
    private static final String IMG = "img/relics/Radio.png";
    private static final String IMG_OTL = "img/relics/outline/Radio.png";


    public Radio() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.RARE,
                LandingSound.SOLID
        );
    }



    private String setDescription(AbstractPlayer.PlayerClass c){
        return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1];
    }

    @Override
    public String getUpdatedDescription() {
        if(player != null){
            return setDescription(player.chosenClass);
        }
        return setDescription(null);
    }

    public void updateDescription(AbstractPlayer.PlayerClass playerClass){
        this.description = setDescription(playerClass);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    public void onEquip(){
        this.counter = 0;
    }

    public void atTurnStart(){
        if(this.counter == -1){
            this.counter += 2;
        }else {
        this.counter++;
        }

        if(this.counter == 3){
            this.counter = 0;
            flash();
            addToBot(new RelicAboveCreatureAction(player, this));
            AbstractDungeon.actionManager.addToBottom(
                    new RecallAction());
        }

    }


    @Override
    public AbstractRelic makeCopy(){
        return new Radio();
    }


}
