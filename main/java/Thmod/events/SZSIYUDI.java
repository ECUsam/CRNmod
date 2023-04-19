package Thmod.events;

import Thmod.Thmod;
import Thmod.cards.Curses.PoorSignal;
import Thmod.cards.Knifegame;
import Thmod.relics.CiXing;
import Thmod.relics.Juanru;
import com.badlogic.gdx.math.RandomXS128;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.effectList;
import static com.megacrit.cardcrawl.helpers.CardLibrary.getCopy;

public class SZSIYUDI extends AbstractImageEvent {

    public static final String ID = "SZSIYUDI";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = "img/event/SZSIYUDI.png";
    private int screenNum = 0;
    private boolean purgeResult = false;


    public SZSIYUDI() {
        super(NAME, DESCRIPTIONS[0], IMG);
        this.imageEventText.updateBodyText(DESCRIPTIONS[0]);

        this.imageEventText.setDialogOption(OPTIONS[0], new PoorSignal(), RandomSIYUDI());
        this.imageEventText.setDialogOption(OPTIONS[1], new Knifegame());
        this.imageEventText.setDialogOption(OPTIONS[2]);


    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON)
            CardCrawlGame.sound.play("SPIN");
    }



    /*     */   public void update() {
        /*  58 */     super.update();
        /*  59 */     purgeLogic();
        /*     */
        /*  61 */     if (this.waitForInput) {
            /*  62 */       buttonEffect(GenericEventDialog.getSelectedOption());
            /*     */     }
        /*     */   }

    @Override
    protected void buttonEffect(int buttonPressed) {

        switch (this.screenNum){
            case 0:
                switch (buttonPressed){
                    case 0:
                        effectList.add(new ShowCardAndObtainEffect(getCopy(PoorSignal.ID), Settings.WIDTH/2.0f, Settings.HEIGHT/2.0f));
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH/2.0f, Settings.HEIGHT/2.0f, RandomSIYUDI());
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);

                        this.imageEventText.clearRemainingOptions();
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                        this.screenNum = 2;
                        break;
                    case 1:
                        AbstractDungeon.player.damage(new DamageInfo(null, 5, DamageInfo.DamageType.HP_LOSS));
                        effectList.add(new ShowCardAndObtainEffect(getCopy(Knifegame.ID), Settings.WIDTH/2.0f, Settings.HEIGHT/2.0f));
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);

                        this.imageEventText.clearRemainingOptions();
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                        this.screenNum = 2;
                        break;
                    case 2:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
                        Thmod.logger.info("发动事件！！！！");

                        this.imageEventText.clearRemainingOptions();
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[4]);
                        this.screenNum = 1;
                        this.purgeResult = true;
                        break;
                }
            case 1:
                if(this.screenNum ==1) {
                    Thmod.logger.info("case1嵌套2");
                    AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), 1, OPTIONS[4], false, false, false, true);
                    this.imageEventText.updateDialogOption(0, OPTIONS[4]);
                    this.screenNum = 2;
                    return;
                }
            case 2:
                openMap();
                break;
        }
        //openMap();

    }

    public AbstractRelic RandomSIYUDI(){
        RandomXS128 randomXS128 = AbstractDungeon.cardRandomRng.random;
        int R = randomXS128.nextInt(2);
        ArrayList<AbstractRelic> SIYUDI = new ArrayList<>();

        SIYUDI.add(new Juanru());
        SIYUDI.add(new CiXing());

        return SIYUDI.get(R).makeCopy();
    }

    /*     */   private void purgeLogic() {
        /* 132 */     if (this.purgeResult && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            /* 133 */       AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            /* 134 */       AbstractDungeon.topLevelEffects.add(new PurgeCardEffect(c, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
            /* 135 */
            /* 136 */       AbstractDungeon.player.masterDeck.removeCard(c);
            /* 137 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
            /*     */
            /* 139 */       this.purgeResult = false;
            /*     */     }
        /*     */   }
    /*     */ }
