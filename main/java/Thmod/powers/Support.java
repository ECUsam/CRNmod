package Thmod.powers;

import Thmod.Thmod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Objects;

public class Support extends AbstractPower {

    public static final String POWER_ID = "Support";
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public boolean RMASUPPORT = true;
    public boolean DIYUSISUPPORT = true;
    public boolean SIYUDISUPPORT = true;
    public boolean KMGSUPPORT = true;
    public boolean HNSSUPPORT = true;
    public boolean SZSUPPORT = true;
    public boolean SIKSUPPORT = true;
    public boolean KNNSUPPORT = true;

    public Support(AbstractCreature owner, int amount){
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = AbstractPower.PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/Support.png");
        this.amount = amount;
    }
    @Override
    public void stackPower(int stackAmount) {
        Thmod.logger.info("=========stackPower发动========");
        this.amount += stackAmount;
        if (this.amount <= 0) {
            this.amount = 0;
        }
        update();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(Objects.equals(power.ID, "Support")){
            update();
        }
    }

    public void update(){

        if(this.amount >= 3 && RMASUPPORT){
            Thmod.logger.info("=========RMA支援");
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new RMASupport(owner, 1), 1));
            RMASUPPORT = false;
        }
        if(this.amount >= 6 && DIYUSISUPPORT){
            Thmod.logger.info("=========DIYUSI支援");
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new DIYUSISupport(owner, 1), 1));
            DIYUSISUPPORT = false;
        }
        if(this.amount >= 10 && SIYUDISUPPORT){
            Thmod.logger.info("=========SIYUDI支援");
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new SIYUDISupport(owner, 1), 1));
            SIYUDISUPPORT = false;
        }
        if(this.amount >= 15 && KMGSUPPORT){
            Thmod.logger.info("=========小麦粉支援");
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new Komugiko(owner), 1));
            KMGSUPPORT = false;
        }
        if(this.amount >= 21 && HNSSUPPORT){
            Thmod.logger.info("=========HNS支援");
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new HNSSupport(owner, 1), 1));
            HNSSUPPORT = false;
        }
        if(this.amount >= 28 && SZSUPPORT){
            Thmod.logger.info("=========SZ支援");
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new SZPower(owner, 1), 1));
            SZSUPPORT = false;
        }
        if(this.amount >= 36 && SIKSUPPORT){
            Thmod.logger.info("=========SIK支援");
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new SIKSupport(owner, 1), 1));
            SIKSUPPORT = false;
        }
        if(this.amount >= 45 && KNNSUPPORT){
            Thmod.logger.info("=========KNN支援");
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                    new KNNSupport(owner)));
            KNNSUPPORT = false;
        }


        if(this.amount <3 && owner.hasPower("RMASupport") && !RMASUPPORT){
            owner.getPower("RMASupport").reducePower(1);
            RMASUPPORT = true;
        }
        if(this.amount<6 && owner.hasPower("DIYUSISupport") && !DIYUSISUPPORT){
            owner.getPower("DIYUSISupport").reducePower(1);
            DIYUSISUPPORT = true;
        }
        if(this.amount<10 && owner.hasPower("SIYUDISupport") && !SIYUDISUPPORT){

            owner.getPower("SIYUDISupport").reducePower(1);
            SIYUDISUPPORT = true;

        }


        Thmod.logger.info("现在的支援数：" +this.amount);
        if (this.amount <= 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Support"));
        }
        Thmod.logger.info("=========stackPower结束========");
    }

    public void reducePower(int reduceAmount) {
        if (this.amount - reduceAmount <= 0) {
            this.fontScale = 8.0F;
            this.amount = 0;
        } else {
            this.fontScale = 8.0F;
            this.amount -= reduceAmount;
        }
        update();
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, Support.POWER_ID));
        }
    }


    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]+DESCRIPTIONS[1]);
    }
}
