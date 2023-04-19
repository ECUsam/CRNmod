package Thmod.cards;

import Thmod.Thmod;
import Thmod.pathes.AbstractCardEnum;
import Thmod.pathes.CustomTags;
import Thmod.powers.SZPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ConfusingCombatants extends CustomCard {
    public static final String ID = "ConfusingCombatants";
    public static final String IMG_PATH = "img/cards/ConfusingCombatants.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int MagicNumber = 1;
    private static final int ATTACK_DMG = 8 ;

    public ConfusingCombatants(){
        super(
                ID,
                NAME,
                IMG_PATH,
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.CRN_COLOR,
                CardRarity.COMMON,
                CardTarget.ALL_ENEMY
        );
        this.magicNumber = this.baseMagicNumber = MagicNumber;
        this.baseDamage = ATTACK_DMG;
        this.tags.add(CustomTags.SHOTS);
        this.baseBlock = this.magicNumber;
    }
    @Override
    public void use(AbstractPlayer player, AbstractMonster monster){
        Thmod.logger.info("basedamage:"+this.baseDamage);
        Thmod.logger.info("magicNumber:"+this.magicNumber);
    for(int i =0;i<this.magicNumber+countPowers();i++){
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(randomMonster, new DamageInfo(player, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    }

    public static int countPowers(){
        int count = 0;
        AbstractCreature player = AbstractDungeon.player;
        if (player.hasPower("DIYUSISupport"))count++;
        if (player.hasPower("SIYUDISupport"))count++;
        if (player.hasPower("RMASupport"))count++;
        if (player.hasPower("SIKSupport"))count++;
        if (player.hasPower("HNSSupport"))count++;
        if (player.hasPower("ICGSupport"))count++;
        if (player.hasPower("MDCNSupport"))count++;
        if (player.hasPower(SZPower.POWER_ID))count++;
        return count;
    }

    @Override
    public void applyPowers(){
        this.baseBlock=countPowers();
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

//TODO:描述
    public AbstractCard makeCopy(){
        return new ConfusingCombatants();
    }

    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(4);
        }
    }
}
