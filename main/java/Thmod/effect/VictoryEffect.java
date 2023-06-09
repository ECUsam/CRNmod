package Thmod.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class VictoryEffect extends AbstractGameEffect {
    private float speed;
    private static TextureAtlas Atlas = null;

    private static com.esotericsoftware.spine.Skeleton Skeleton;
    private static AnimationState State;
    private static AnimationStateData StateData;
    private static SkeletonData Data;

    public VictoryEffect() {
        this.speed = this.speed;
        this.duration = 3.0F;

        loadanimation(this.speed);
    }


    private static void loadanimation(float timeScale) {
        Atlas = new TextureAtlas(Gdx.files.internal("img/VictoryIMG/Yukari_Victory.atlas"));
        SkeletonJson json = new SkeletonJson(Atlas);
        json.setScale(Settings.scale / 1.0F);
        Data = json.readSkeletonData(Gdx.files.internal("img/VictoryIMG/Yukari_Victory.json"));

        Skeleton = new Skeleton(Data);
        Skeleton.setColor(Color.WHITE);
        StateData = new AnimationStateData(Data);
        State = new AnimationState(StateData);
        StateData.setDefaultMix(0.2F);

        State.setTimeScale(1.0F * timeScale);
        Skeleton.setPosition(0.0F, 0.0F);

        State.setAnimation(0, "normal", true);
    }


    public void update() {}

    public void render(SpriteBatch sb) {
        State.update(Gdx.graphics.getDeltaTime());
        State.apply(Skeleton);
        Skeleton.updateWorldTransform();
        Skeleton.setColor(Color.WHITE);
        Skeleton.setFlip(false, false);

        sb.end();
        CardCrawlGame.psb.begin();
        AbstractCreature.sr.draw(CardCrawlGame.psb, Skeleton);
        CardCrawlGame.psb.end();
        sb.begin();
    }

    public void dispose() {}
}
