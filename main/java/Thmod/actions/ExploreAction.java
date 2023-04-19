package Thmod.actions;

import Thmod.Thmod;
import Thmod.powers.*;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExploreAction extends AbstractGameAction {

    private final AbstractCreature player;
    private final int count;


    public ExploreAction(AbstractCreature player, int magicNumber){
        this.player = player;
        this.count = magicNumber;
    }

    public void RandomFunction(int R) {

        RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
        int RandomS = randomXS128.nextInt(5);

        Thmod.logger.info("随机数：" + R + " 探索发动");
        switch (R) {
            case 0:
                addToBot(new DrawCardAction(player, 2, false));
                break;
            case 1:
                addToBot(new GainEnergyAction(1));
                break;
            case 2:
                switch (RandomS){
                    case 0:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new DIYUSISupport(player, 1), 1));
                        break;
                    case 1:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new SIYUDISupport(player, 1), 1));
                        break;
                    case 2:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new SIKSupport(player, 1), 1));
                        break;
                    case 3:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new RMASupport(player, 1), 1));
                        break;
                    case 4:
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(player, player,
                                new BeerBeerPower(player), 1));
                        break;
                }
                break;
        }
    }

    @Override
    public void update() {
        RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
        int R1 = randomXS128.nextInt(3);
        int R2 = randomXS128.nextInt(3);
        while (R1 == R2){
            R2 = randomXS128.nextInt(3);
        }

        if(this.count == 1){
            RandomFunction(R1);
        }else {
            RandomFunction(R1);
            RandomFunction(R2);
        }
        this.isDone = true;
        }
    }

