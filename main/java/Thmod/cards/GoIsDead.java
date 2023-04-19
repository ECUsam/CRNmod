package Thmod.cards;

import Thmod.Thmod;
import Thmod.actions.FlyingOrbEffect;
import Thmod.pathes.AbstractCardEnum;
import Thmod.powers.*;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class GoIsDead extends CustomCard
{
    //从.json文件中提取键名为Strike_CRN的信息
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("GoIsDead");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int UP = 4;
    private static final int Magic = 8;
    public static final String ID = "GoIsDead";
    public static final String IMG_PATH = "img/cards/GoIsDead.png";

    public GoIsDead() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.CRN_COLOR, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = Magic;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = CostAllSupport();
        int x = MathUtils.round(m.maxHealth * this.magicNumber * 0.01F);
        Thmod.logger.info("特效要来辣");
        for (int i = 0; i < count; i++) {
            addToBot(new VFXAction(new FlyingOrbEffect(p.hb.cX, p.hb.cY, m)));
            addToBot(new LoseHPAction(m, m, x));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new GoIsDead();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            upgradeName();
            upgradeMagicNumber(UP);
        }
    }

    public int CostAllSupport(){
        AbstractCreature player = AbstractDungeon.player;
        int count = 0;
        if(player.hasPower(DIYUSISupport.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, DIYUSISupport.POWER_ID));
            count++;
        }
        if(player.hasPower(SIYUDISupport.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, SIYUDISupport.POWER_ID));
            count++;
        }
        if(player.hasPower(RMASupport.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, RMASupport.POWER_ID));
            count++;
        }
        if(player.hasPower(ICGSupport.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, ICGSupport.POWER_ID));
            count++;
        }
        if(player.hasPower(SZPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, SZPower.POWER_ID));
            count++;
        }
        if(player.hasPower(HNSSupport.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, HNSSupport.POWER_ID));
            count++;
        }
        if(player.hasPower(MDCNSupport.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, MDCNSupport.POWER_ID));
            count++;
        }
        if(player.hasPower(SIKSupport.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, SIKSupport.POWER_ID));
            count++;
        }
        if(player.hasPower(MilkPower.POWER_ID)){
            addToBot(new RemoveSpecificPowerAction(player, player, MilkPower.POWER_ID));
            count++;
        }
        return count;
    }
}
