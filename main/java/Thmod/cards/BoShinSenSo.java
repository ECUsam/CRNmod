package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class BoShinSenSo extends CustomCard
{
    //从.json文件中提取键名为Strike_CRN的信息
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BoShinSenSo");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int BLOCK_AMT = 8;
    private static final int UPGRADE_PLUS_BLOCK = 5;
    public static final String ID = "BoShinSenSo";
    public static final String IMG_PATH = "img/cards/BoShinSenSo.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public BoShinSenSo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.CRN_COLOR, CardRarity.UNCOMMON, CardTarget.SELF);
        //添加基础防御标签和将格挡设为5
        this.baseBlock = BLOCK_AMT;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //使用卡牌时触发的动作
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        int num = 0;
        for(AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if(card.type == CardType.SKILL){
                num++;
            }
        }
        if(num>0)addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, num*2), num*2));
    }

    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return new BoShinSenSo();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
