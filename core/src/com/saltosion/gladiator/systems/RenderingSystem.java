package com.saltosion.gladiator.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.saltosion.gladiator.components.CPhysics;
import com.saltosion.gladiator.components.CRenderedObject;
import com.saltosion.gladiator.util.AppUtil;
import com.saltosion.gladiator.util.SpriteSequence;

public class RenderingSystem extends EntitySystem {

	private final ComponentMapper<CRenderedObject> rom = ComponentMapper.getFor(CRenderedObject.class);
	private final ComponentMapper<CPhysics> pm = ComponentMapper.getFor(CPhysics.class);
	private ImmutableArray<Entity> entities;

	private SpriteBatch batch;
	private ShapeRenderer debugRenderer;
	private OrthographicCamera camera;

	private boolean debug = true;
	private final Color debugColor = new Color(0, 1, 0, 1);

	@Override
	public void addedToEngine(Engine engine) {

		updateEntities(engine);

		batch = new SpriteBatch();
		debugRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1, 1);
	}

	public void setViewport(int width, int height) {
		camera.setToOrtho(false, width, height);
	}

	@Override
	public void update(float deltaTime) {
		CPhysics phys = pm.get(AppUtil.player);
		camera.position.set(phys.getPosition().x, phys.getPosition().y, 0);
		camera.update();

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int i = 0; i < entities.size(); i++) {
			CRenderedObject renderedObject = rom.get(entities.get(i));
			SpriteSequence currSequence = renderedObject.getSequence(renderedObject.getCurrentSequence());
			int currFrame = (int) Math.floor(renderedObject.getCurrentFrame());
			Sprite currSprite = currSequence.getSprite(currFrame);

			CPhysics physics = pm.get(entities.get(i));

			int spriteHeight = currSprite.getRegionHeight();
			int spriteWidth = currSprite.getRegionWidth();

			currSprite.setPosition(physics.getPosition().x - spriteWidth / 2,
					physics.getPosition().y - spriteHeight / 2);
			currSprite.draw(batch);

			float nextFrame = renderedObject.getCurrentFrame() + deltaTime * currSequence.getPlayspeed();
			renderedObject.setCurrentFrame(nextFrame % currSequence.frameCount());
		}
		batch.end();

		if (debug) {
			debugRenderer.setProjectionMatrix(camera.combined);
			debugRenderer.begin(ShapeType.Line);
			debugRenderer.setColor(debugColor);
			for (int i = 0; i < entities.size(); i++) {
				CPhysics physics = pm.get(entities.get(i));
				float x0 = physics.getPosition().x - physics.getSize().x / 2;
				float x1 = physics.getPosition().x + physics.getSize().x / 2;
				float y0 = physics.getPosition().y - physics.getSize().y / 2;
				float y1 = physics.getPosition().y + physics.getSize().y / 2;

				debugRenderer.line(x0, y0, x1, y0);
				debugRenderer.line(x1, y0, x1, y1);
				debugRenderer.line(x1, y1, x0, y1);
				debugRenderer.line(x0, y1, x0, y0);
			}
			debugRenderer.end();
		}
	}

	public void updateEntities(Engine engine) {
		entities = engine.getEntitiesFor(Family.getFor(CRenderedObject.class, CPhysics.class));
	}

	public boolean getDebug() {
		return this.debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
