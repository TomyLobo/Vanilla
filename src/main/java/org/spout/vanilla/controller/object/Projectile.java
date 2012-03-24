/*
 * This file is part of vanilla (http://www.spout.org/).
 *
 * vanilla is licensed under the SpoutDev License Version 1.
 *
 * vanilla is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * vanilla is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.vanilla.controller.object;

import org.spout.api.entity.Entity;
import org.spout.api.math.Quaternion;
import org.spout.api.math.Vector3;
import org.spout.vanilla.controller.VanillaControllerType;

/**
 * Represents a controller that can project through the air.
 */
public class Projectile extends MovingSubstance {
	private Entity shooter;
	private Quaternion rotation;

	public Projectile(VanillaControllerType type, Quaternion rotation, int maxSpeed) {
		super(type);
		this.setMaxSpeed(maxSpeed);
		this.rotation = rotation;
		this.setVelocity(new Vector3(maxSpeed, 0, 0));
	}

	@Override
	public void onAttached() {
		Vector3 rotation = this.rotation.getAxisAngles();
		getParent().roll(rotation.getX());
		getParent().yaw(rotation.getY());
		getParent().pitch(rotation.getZ());
	}

	@Override
	public void onTick(float dt) {
		//position += velocity.transform(rotation) * dt;
		//parent.setPosition(parent.getPosition().add(velocity.transform(t.getRotation()).multiply(dt)));
	}

	/**
	 * Sets the controller that shot this projectile
	 * @param entity - the shooter
	 */
	public void setShooter(Entity entity) {
		shooter = entity;
	}

	/**
	 * Gets the controller that shot this projectile
	 * @return the shooter controller
	 */
	public Entity getShooter() {
		return shooter;
	}
}
