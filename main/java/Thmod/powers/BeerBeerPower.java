package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BeerBeerPower extends AbstractPower {
    public static final String POWER_ID = "BeerBeerPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BeerBeerPower(AbstractCreature owner){
        Thmod.logger.info("BeerBeerPower初始化");
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/BeerBeerPower.png");
        Thmod.logger.info("BeerBeerPower初始化完成");
    }

    @Override
    public int onPlayerGainedBlock(int blockAmount) {
        return blockAmount+3;
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

}
