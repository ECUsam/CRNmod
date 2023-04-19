package Thmod.cards;

import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DefensiveCounterattack extends CustomCard {
    public static final String ID = "DefensiveCounterattack";
    public static final String IMG_PATH = "img/cards/DefensiveCounterattack.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 1;
    private static final int UPGRADE_PLUS_DMG = 3;
    private static final int UPGRADE_PLUS_BLK = 2;
    private static final int ATTACK_DMG = 5 ;
    private static final int BlockNumber = 3 ;
    public DefensiveCounterattack(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.COMMON,
                CardTarget.SELF_AND_ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = ATTACK_DMG;
        this.tags.add(CustomTags.SLAIN);
        this.baseBlock = BlockNumber;
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(monster,
                        new DamageInfo(player,
                                this.damage,
                                this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (AbstractDungeon.player.currentBlock == 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new DrawCardAction(player, this.magicNumber)
            );
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(player, player, this.block));
    }


    public AbstractCard makeCopy(){
        return new DefensiveCounterattack();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBlock(UPGRADE_PLUS_BLK);
        }
    }
}
