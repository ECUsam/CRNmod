package Thmod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NoDIYUSI extends AbstractPower {
    public static final String POWER_ID = "NoDIYUSI";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack
            .getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NoDIYUSI(AbstractCreature owner){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.DEBUFF;
        updateDescription();
        this.img = new Texture("img/powers/NoDIYUSI.png");
    }



    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

}