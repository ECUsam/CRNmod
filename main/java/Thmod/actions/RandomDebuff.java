package Thmod.actions;

import Thmod.Thmod;
import Thmod.powers.BloodPower;
import Thmod.powers.Burning;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RandomDebuff extends AbstractGameAction {
    public static final Logger logger = LogManager.getLogger(Thmod.class.getName());

    public int MagicNumber;
    public AbstractCreature m;
    AbstractPlayer p = AbstractDungeon.player;

    public RandomDebuff(AbstractCreature m, int MagicNumber){
        this.MagicNumber = MagicNumber;
        this.m = m;
    }

    public void update() {

        if (!m.halfDead && !m.isDead && !m.isDying) {
            RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
            int R = randomXS128.nextInt(12);
            logger.info("随机数：" + R + " 施加随机效果");
            switch (R) {
                case 0:
                case 3:
                    addToBot(new ApplyPowerAction(this.m, p, new WeakPower(this.m, MagicNumber, false)));
                    break;
                case 1:
                    addToBot(new ApplyPowerAction(this.m, p, new VulnerablePower(this.m, MagicNumber, false)));
                    addToBot(new ApplyPowerAction(this.m, p, new Burning(this.m, MagicNumber)));
                    break;
                case 4:
                case 8:
                    addToBot(new ApplyPowerAction(this.m, p, new VulnerablePower(this.m, MagicNumber, false)));
                    break;
                case 9:
                    addToBot(new ApplyPowerAction(this.m, p, new BloodPower(this.m, MagicNumber)));
                    break;
                case 2:
                case 5:
                case 6:
                case 7:
                    break;
            }
        }
        this.isDone = true;
    }
}
