package com.ray3k.demonstration;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Core extends ApplicationAdapter {
    Texture jungleTexture;
    Texture peachTexture;
    SpriteBatch batch;
	
	@Override
	public void create () {
        jungleTexture = new Texture("jungle.png");
        peachTexture = new Texture("peach.png");
        batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batch.begin();
        batch.draw(jungleTexture, 0, 0);
        batch.draw(peachTexture, Gdx.input.getX() - peachTexture.getWidth() / 2f, Gdx.graphics.getHeight() - Gdx.input.getY() - peachTexture.getHeight() / 2f);
        batch.end();
	}
	
	@Override
	public void dispose () {
        jungleTexture.dispose();
        batch.dispose();
	}
}
