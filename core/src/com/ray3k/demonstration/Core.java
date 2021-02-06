package com.ray3k.demonstration;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.crashinvaders.vfx.VfxManager;
import com.crashinvaders.vfx.effects.BloomEffect;
import com.crashinvaders.vfx.effects.WaterDistortionEffect;

public class Core extends ApplicationAdapter {
    Texture jungleTexture;
    Texture peachTexture;
    SpriteBatch batch;
    VfxManager vfxManager;
    WaterDistortionEffect waterEffect;
    BloomEffect bloomEffect;
    
    @Override
    public void create() {
        vfxManager = new VfxManager(Format.RGBA8888);
        waterEffect = new WaterDistortionEffect(10f, .5f);
        vfxManager.addEffect(waterEffect);
        
        bloomEffect = new BloomEffect();
        vfxManager.addEffect(bloomEffect);
        
        jungleTexture = new Texture("jungle.png");
        peachTexture = new Texture("peach.png");
        batch = new SpriteBatch();
    }
    
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        bloomEffect.setBloomIntensity((float) Gdx.input.getX() / Gdx.graphics.getWidth() * 20);
        vfxManager.update(Gdx.graphics.getDeltaTime());
        
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        batch.begin();
        batch.draw(jungleTexture, 0, 0);
        batch.draw(peachTexture, Gdx.input.getX() - peachTexture.getWidth() / 2f, Gdx.graphics.getHeight() - Gdx.input.getY() - peachTexture.getHeight() / 2f);
        batch.end();
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    }
    
    @Override
    public void dispose() {
        jungleTexture.dispose();
        peachTexture.dispose();
        batch.dispose();
        vfxManager.dispose();
        waterEffect.dispose();
        bloomEffect.dispose();
    }
    
    @Override
    public void resize(int width, int height) {
        vfxManager.resize(width, height);
    }
}
