package Thmod.monsters;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Car extends AbstractMonster {

    public static final String ID = "Car";
    public static final String NAME = "标识附件：机动车";
    private static final int HP = 45;
    private static final int DMG_A = 8;
    private static final int DMG_MULTI_A = 4;
    private static final int DMG_MULTI = 3;
    private static final int DMG = 4;
    private int block, block_upg;
    private static final int BLOCK = 7;
    private static final int BLOCK_A = 10;
    private static final int BLOCK_UPG = 12;
    private static final int BLOCK_UPG_A = 15;
    private static final String MODEL_ATLAS = "img/monsters/car/car.atlas";
    private static final String MODEL_JSON = "img/monsters/car/car.json";

    public Car(float x, float y) {
        super(NAME, ID, HP, 0.0F, 0.0F, 140.0F, 170.0f, null, x, y + 25.0F);

        loadAnimation(MODEL_ATLAS, MODEL_JSON, 2.1F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "normal", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        e.setTimeScale(1.0F);


        if (AbstractDungeon.ascensionLevel >= 8) {
            this.setHp((int) (HP * 1.3));
        } else {
            this.setHp(HP);
        }
        if (AbstractDungeon.ascensionLevel >= 2) {
            this.damage.add(new DamageInfo(this, DMG_A));
            this.damage.add(new DamageInfo(this, DMG_MULTI_A));
            this.block = BLOCK_A;
            this.block_upg = BLOCK_UPG_A;
        } else {
            this.damage.add(new DamageInfo(this, DMG));
            this.damage.add(new DamageInfo(this, DMG_MULTI));
            this.block = BLOCK;
            this.block_upg = BLOCK_UPG;
        }
    }
    @Override
    public void takeTurn() {
        int i;
        switch (this.nextMove){
            case 1:

        }


    }

    @Override
    protected void getMove(int i) {

    }
}
