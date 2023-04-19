package Thmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CorrosivityActualAction extends AbstractGameAction {

    @Override
    public void update() {
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            this.isDone = true;
            return;
        }
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            for(AbstractPower power : m.powers){
                if(power.type == AbstractPower.PowerType.DEBUFF){
                    m.getPower(power.ID).amount *= 2;
                }
            }
        }
        this.isDone = true;
    }
}
