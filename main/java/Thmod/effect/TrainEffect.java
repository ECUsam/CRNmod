package Thmod.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class TrainEffect extends AbstractGameEffect {
    public static final String TRAIN = "img\\vtf\\YukariTrain.png";
    private static final Texture TRAIN_TEXTURE = new Texture(TRAIN);

    private TextureRegion TRAIN_REGION;

    private static final float DURATION = 4.0F;
    private float y;
    private float offset;
    private float start = 2149.0F;
    private float end = -300.0F;

    private float graphicsAnimation;
    private float scaleWidth;
    private float scaleHeight;

    public TrainEffect() {
        this.TRAIN_REGION = new TextureRegion(TRAIN_TEXTURE);
        this.y = AbstractDungeon.floorY + 100.0F * Settings.scale;
        this.color = Color.WHITE.cpy();
        this.color.a = 0.0F;
        this.duration = this.startingDuration = 4.0F;
        this.graphicsAnimation = 0.0F;
        this.offset = this.start;

        this.scaleWidth = 1.0F * Settings.scale;
        this.scaleHeight = Settings.scale;
    }


    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.graphicsAnimation += Gdx.graphics.getDeltaTime();
        if (this.graphicsAnimation <= 0.5F) {

            this.color.a = this.graphicsAnimation * 4.0F;
            if (this.color.a > 1.0F) {
                this.color.a = 1.0F;
            }
        }
        if (this.graphicsAnimation <= 4.0F) {
            this.offset = Interpolation.linear.apply(this.start, this.end, this.graphicsAnimation * 1.5F);
        }
        if (this.duration <= 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.TRAIN_REGION, (this.offset + this.TRAIN_REGION.getRegionWidth()) * this.scaleWidth, this.y - this.TRAIN_REGION.getRegionHeight() * this.scaleHeight / 2.0F, 0.0F, 0.0F, this.TRAIN_REGION.getRegionWidth(), this.TRAIN_REGION.getRegionHeight(), this.scaleWidth, this.scaleHeight, 0.0F);
        sb.draw(this.TRAIN_REGION, (this.offset + (this.TRAIN_REGION.getRegionWidth() * 2)) * this.scaleWidth, this.y - this.TRAIN_REGION.getRegionHeight() * this.scaleHeight / 2.0F, 0.0F, 0.0F, this.TRAIN_REGION.getRegionWidth(), this.TRAIN_REGION.getRegionHeight(), this.scaleWidth, this.scaleHeight, 0.0F);
        sb.draw(this.TRAIN_REGION, (this.offset + (this.TRAIN_REGION.getRegionWidth() * 3)) * this.scaleWidth, this.y - this.TRAIN_REGION.getRegionHeight() * this.scaleHeight / 2.0F, 0.0F, 0.0F, this.TRAIN_REGION.getRegionWidth(), this.TRAIN_REGION.getRegionHeight(), this.scaleWidth, this.scaleHeight, 0.0F);
        sb.draw(this.TRAIN_REGION, (this.offset + (this.TRAIN_REGION.getRegionWidth() * 4)) * this.scaleWidth, this.y - this.TRAIN_REGION.getRegionHeight() * this.scaleHeight / 2.0F, 0.0F, 0.0F, this.TRAIN_REGION.getRegionWidth(), this.TRAIN_REGION.getRegionHeight(), this.scaleWidth, this.scaleHeight, 0.0F);
    }

    public void dispose() {}
}
