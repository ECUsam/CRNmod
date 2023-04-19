package Thmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class ReloadAction extends AbstractGameAction {

    private final int CardNum;
    private final int updatanum;

    public ReloadAction(int CardNum, int updatanum){
        this.CardNum = CardNum;
        this.updatanum = updatanum;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToBottom(new DiscardAction(player, player, CardNum, false));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.CardNum+this.updatanum, new ReloadAction2()));
        this.isDone = true;
    }
}
