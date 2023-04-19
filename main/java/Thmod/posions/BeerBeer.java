package Thmod.posions;

import Thmod.powers.BeerBeerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BeerBeer
        extends AbstractPotion {

    public static final String POTION_ID = "BeerBeer";
    private static final PotionStrings potionStrings =
            CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public BeerBeer() {
        super(
                NAME,
                POTION_ID,
                PotionRarity.UNCOMMON,
                PotionSize.H,
                PotionColor.POWER
        );
        this.potency = getPotency();
        this.description = DESCRIPTIONS[0];
        this.isThrown = true;
        this.targetRequired = false;
        this.tips.add(
                new PowerTip(this.name, this.description)
        );
        this.tips.add(
                new PowerTip(DESCRIPTIONS[1], DESCRIPTIONS[2])
        );
    }

    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(target, target, new BeerBeerPower(target))
            );
        }
    }

    public AbstractPotion makeCopy() {
        return new BeerBeer();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}
