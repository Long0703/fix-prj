package com.paradise_seeker.game.entity.monster.creep;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.paradise_seeker.game.entity.monster.Monster;
import com.paradise_seeker.game.entity.Player;

public class CyanBat extends Monster {
    public CyanBat(float x, float y) {
    	super(new Rectangle(x, y, 10f, 6f), 1000f, 500f, 1000f, 500f, 50f, 2f, x, y); // HP, speed, cleaveDamage
        this.spawnX = x;
        this.spawnY = y;
        this.spriteWidth = 1.2f;
        this.spriteHeight = 1.2f;
        updateBounds(); // Đồng bộ lại bounds

        loadAnimations();
        this.currentFrame = walkRight.getKeyFrame(0f);
        this.cleaveRange = 2f; // Nhỏ hơn Boss
        updateBounds();

    }
    @Override
    protected float getScaleMultiplier() {
        return 2f;
    }

    @Override
    protected void loadAnimations() {
        walkRight = loadAnimationWithPadding("images/Entity/characters/monsters/creep/map2/cyan_bat/right/walk/", "walk", 8, ".png", 1);
        walkLeft  = loadAnimationWithPadding("images/Entity/characters/monsters/creep/map2/cyan_bat/left/walk/", "walk", 8, ".png", 1);

        idleRight = loadAnimationWithPadding("images/Entity/characters/monsters/creep/map2/cyan_bat/right/idle/", "idle", 11, ".png", 1);
        idleLeft  = loadAnimationWithPadding("images/Entity/characters/monsters/creep/map2/cyan_bat/left/idle/", "idle", 11, ".png", 1);

        cleaveRight = loadAnimationWithPadding("images/Entity/characters/monsters/creep/map2/cyan_bat/right/atk/", "attack", 8, ".png", 1);
        cleaveLeft  = loadAnimationWithPadding("images/Entity/characters/monsters/creep/map2/cyan_bat/left/atk/", "attack", 8, ".png", 1);

        takeHitRight = loadAnimationWithPadding("images/Entity/characters/monsters/creep/map2/cyan_bat/right/hit/", "hit", 3, ".png", 1);
        takeHitLeft  = loadAnimationWithPadding("images/Entity/characters/monsters/creep/map2/cyan_bat/left/hit/", "hit", 3, ".png", 1);

        deathRight = idleRight;
        deathLeft  = idleLeft;
    }

  

    private Animation<TextureRegion> loadAnimationWithPadding(String folder, String prefix, int frameCount, String suffix, int startIndex) {
        TextureRegion[] frames = new TextureRegion[frameCount];
        for (int i = 0; i < frameCount; i++) {
            String filename = String.format("%s%s%02d%s", folder, prefix, i + startIndex, suffix);
            Texture texture = new Texture(Gdx.files.internal(filename));
            frames[i] = new TextureRegion(texture);
        }
        return new Animation<>(0.1f, frames);
    }
    @Override
    public void render(SpriteBatch batch) {
        render(batch, null); // hoặc truyền player nếu có
    }

    public void render(SpriteBatch batch, Player player) {
        if (isDead) return;
        super.render(batch, player);
        batch.draw(currentFrame, bounds.x, bounds.y, spriteWidth, spriteHeight);
    }


}