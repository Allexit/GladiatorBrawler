/**
 * GladiatorBrawler is a 2D swordfighting game.
 * Copyright (C) 2015 Jeasonfire/Allexit
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.saltosion.gladiator.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.saltosion.gladiator.listeners.CollisionListener;
import com.saltosion.gladiator.util.Direction;

public class CPhysics extends Component {

	private final Vector2 position = new Vector2();
	private final Vector2 velocity = new Vector2();
	private final Vector2 simVelocity = new Vector2();
	private final Vector2 size = new Vector2();
	private float movespeed = 15f, jumpForce = 35f, gravity = 100f, drag = 30f;
	private CollisionListener collisionListener = null;
	private float zParallax = 1;

	private boolean movable = true;
	private boolean gravityApplied = true;
	private boolean processCollisions = true;
	private boolean ghost = false;
	private boolean grounded = true;

	// Movement (/input) vars
	public boolean movingLeft = false;
	public boolean movingRight = false;
	public boolean jumping = false;

	// Stores information about the direction last time moved in
	public boolean movedLeftLast = false;

	// Stores a float that tells how long the physics object must wait until it can play sounds with it's walking again.
	public float stepCD = 0;

	/**
	 * @param movable Toggles if the entity can move by itself
	 * @return Returns the instance this methdod was called from
	 */
	public CPhysics setMovable(boolean movable) {
		this.movable = movable;
		return this;
	}

	/**
	 * @param gravityApplied Toggles if the entity is affected by gravity
	 * @return Returns the instance this methdod was called from
	 */
	public CPhysics setGravityApplied(boolean gravityApplied) {
		this.gravityApplied = gravityApplied;
		return this;
	}

	/**
	 * @param processCollisions Toggles if the entity processes collisions
	 * @return Returns the instance this methdod was called from
	 */
	public CPhysics setProcessCollisions(boolean processCollisions) {
		this.processCollisions = processCollisions;
		return this;
	}

	/**
	 * @param ghost Toggles if the entity is affected by collisions (will call
	 * collision listener anyway if dynamic == true)
	 * @return Returns the instance this methdod was called from
	 */
	public CPhysics setGhost(boolean ghost) {
		this.ghost = ghost;
		return this;
	}

	public CPhysics setSize(float w, float h) {
		this.size.set(w, h);
		return this;
	}

	public CPhysics setSize(Vector2 size) {
		this.size.set(size);
		return this;
	}

	public CPhysics setPosition(float x, float y) {
		this.position.set(x, y);
		return this;
	}

	public CPhysics setPosition(Vector2 pos) {
		this.position.set(pos);
		return this;
	}

	public CPhysics setVelocity(float x, float y) {
		this.velocity.set(x, y);
		return this;
	}

	public CPhysics setVelocity(Vector2 vel) {
		this.velocity.set(vel);
		return this;
	}

	/**
	 * This velocity can be set externally and will always end up being 0 after
	 * some time that depends on the value of the drag variable
	 *
	 * @param x The x part of the new sim velocity
	 * @param y The y part of the new sim velocity
	 * @return The host component
	 */
	public CPhysics setSimVelocity(float x, float y) {
		this.simVelocity.set(x, y);
		return this;
	}

	/**
	 * This velocity can be set externally and will always end up being 0 after
	 * some time that depends on the value of the drag variable
	 *
	 * @param simVel The new sim velocity
	 * @return The host component
	 */
	public CPhysics setSimVelocity(Vector2 simVel) {
		this.simVelocity.set(simVel);
		return this;
	}

	public CPhysics setMoveSpeed(float movespeed) {
		this.movespeed = movespeed;
		return this;
	}

	public CPhysics setJumpForce(float jumpForce) {
		this.jumpForce = jumpForce;
		return this;
	}

	public CPhysics setGravity(float gravity) {
		this.gravity = gravity;
		return this;
	}

	public CPhysics setDrag(float drag) {
		this.drag = drag;
		return this;
	}

	public CPhysics setCollisionListener(CollisionListener collisionListener) {
		this.collisionListener = collisionListener;
		return this;
	}

	public CPhysics setGrounded(boolean grounded) {
		this.grounded = grounded;
		return this;
	}

	public CPhysics setDirection(Direction dir) {
		if (dir == Direction.RIGHT) {
			this.movedLeftLast = false;
		}
		if (dir == Direction.LEFT) {
			this.movedLeftLast = true;
		}
		return this;
	}

	public Vector2 getPosition() {
		return this.position;
	}

	public Vector2 getVelocity() {
		return this.velocity;
	}

	public Vector2 getSimVelocity() {
		return this.simVelocity;
	}

	public Vector2 getSize() {
		return this.size;
	}

	public float getMovespeed() {
		return this.movespeed;
	}

	public float getJumpForce() {
		return this.jumpForce;
	}

	public float getGravity() {
		return this.gravity;
	}

	public float getDrag() {
		return this.drag;
	}

	public CollisionListener getCollisionListener() {
		return this.collisionListener;
	}

	public boolean isMovable() {
		return this.movable;
	}

	public boolean isGravityApplied() {
		return this.gravityApplied;
	}

	public boolean isProcessCollisions() {
		return this.processCollisions;
	}

	public boolean isGhost() {
		return this.ghost;
	}

	public boolean isGrounded() {
		return this.grounded;
	}

	public CPhysics setZParallax(float zParallax) {
		this.zParallax = zParallax;
		return this;
	}

	public float getZParallax() {
		return this.zParallax;
	}

}
