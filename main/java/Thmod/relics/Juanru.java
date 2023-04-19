package Thmod.relics;

import Thmod.Thmod;
import Thmod.powers.BloodPower;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType.THORNS;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class Juanru extends CustomRelic {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());
    public static final String ID = "Juanru";
    private static final String IMG = "img/relics/Juanru.png";
    private static final String IMG_OTL = "img/relics/outline/Juanru.png";

    public Juanru() {
        super(
                ID,
                ImageMaster.loadImage(IMG),
                ImageMaster.loadImage(IMG_OTL),
                RelicTier.SPECIAL,
                LandingSound.FLAT
        );
    }

    @Override
    public void atTurnStart() {
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        addToBot(new DamageAction(randomMonster, new DamageInfo(player, 3, THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(randomMonster, player,
                new BloodPower(randomMonster,2),
                2, true, AbstractGameAction.AttackEffect.POISON));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy(){
        return new Juanru();
    }


}
