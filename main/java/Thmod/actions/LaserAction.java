package Thmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class LaserAction extends AbstractGameAction {

    public LaserAction(){
    }
    @Override
    public void update() {
        AbstractCreature player = AbstractDungeon.player;
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        int damage = 42;
        addToBot(new VFXAction(new MindblastEffect(player.dialogX, player.dialogY, player.flipHorizontal)));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(randomMonster, new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL), AttackEffect.NONE));
        this.isDone = true;
    }
}
