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
package com.saltosion.gladiator.gui.creators;

import com.saltosion.gladiator.gui.nodes.TextNode;
import com.saltosion.gladiator.util.AppUtil;

public class InGameGUICreator implements GUICreator {

	private TextNode levelChangeText;

	@Override
	public void create() {
		levelChangeText = new TextNode("level_change_text", "Round X");
		levelChangeText.setPosition(0.435f, 0.5f);
		AppUtil.guiManager.getRootNode().addChild(levelChangeText);
	}

	public TextNode getLevelChangeText() {
		return this.levelChangeText;
	}

}
