package Thmod.actions;

import Thmod.powers.BloodPower;
import Thmod.powers.Burning;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class CorrosivityAction extends AbstractGameAction {

    public CorrosivityAction(){
    }

    @Override
    public void update() {
        AbstractPlayer player = AbstractDungeon.player;
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        addToBot(new ApplyPowerAction(randomMonster, player,
                new PoisonPower(randomMonster, player, 1),
                1, true, AbstractGameAction.AttackEffect.POISON));
        addToBot(new ApplyPowerAction(randomMonster, player,
                new Burning(randomMonster, 2),
                2, true, AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(randomMonster, player,
                new VulnerablePower(randomMonster,1, false),
                1, true, AttackEffect.POISON));
        addToBot(new ApplyPowerAction(randomMonster, player,
                new BloodPower(randomMonster,2),
                2, true, AttackEffect.POISON));
        this.isDone = true;
    }
}
