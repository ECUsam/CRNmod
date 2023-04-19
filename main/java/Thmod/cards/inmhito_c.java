package Thmod.cards;

import Thmod.actions.IfUseSupport;
import Thmod.actions.IfUseSupport_inm;
import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import Thmod.powers.Support;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class inmhito_c extends CustomCard {
    public static final String ID = "inmhito_c";
    public static final String IMG_PATH = "img/cards/inmhito_c.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int MagicNumber = 3;
    private static final int UPGRADE_PLUS_DMG = 7;
    private static final int ATTACK_DMG = 14 ;
    public inmhito_c(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = ATTACK_DMG;
        this.tags.add(CustomTags.SLAIN);
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(monster,
                        new DamageInfo(player,
                                this.damage,
                                this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_HEAVY));

        if(player.hasPower(Support.POWER_ID)){
            if(player.getPower(Support.POWER_ID).amount>2){
                int num = 0;
                for(AbstractPower power : monster.powers){
                    if(power.type== AbstractPower.PowerType.DEBUFF)num++;
                }
                AbstractDungeon.actionManager.addToBottom(
                        new IfUseSupport_inm(this.magicNumber, num));
            }
        }
    }


    public AbstractCard makeCopy(){
        return new inmhito_c();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(-1);
        }
    }
}
