/*
 * Copyright (C) 2012-2016 Frank Baumann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.dre2n.dungeonsxl.sign;

import io.github.dre2n.commons.util.NumberUtil;
import io.github.dre2n.dungeonsxl.game.GameChest;
import io.github.dre2n.dungeonsxl.game.GameWorld;
import org.bukkit.Material;
import org.bukkit.block.Sign;

/**
 * @author Frank Baumann, Daniel Saukel
 */
public class ChestSign extends DSign {

    private DSignType type = DSignTypeDefault.CHEST;

    // Variables
    private double moneyReward;

    public ChestSign(Sign sign, GameWorld gameWorld) {
        super(sign, gameWorld);
    }

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public void onInit() {
        String lines[] = getSign().getLines();
        if (!lines[1].isEmpty()) {
            moneyReward = NumberUtil.parseDouble(lines[1]);
        }

        for (int i = -1; i <= 1; i++) {
            if (getSign().getBlock().getRelative(i, 0, 0).getType() == Material.CHEST) {
                new GameChest(getSign().getBlock().getRelative(i, 0, 0), getGameWorld(), moneyReward);
            }

            if (getSign().getBlock().getRelative(0, 0, i).getType() == Material.CHEST) {
                new GameChest(getSign().getBlock().getRelative(0, 0, i), getGameWorld(), moneyReward);
            }

            if (getSign().getBlock().getRelative(0, i, 0).getType() == Material.CHEST) {
                new GameChest(getSign().getBlock().getRelative(0, i, 0), getGameWorld(), moneyReward);
            }
        }

        getSign().getBlock().setType(Material.AIR);
    }

    @Override
    public DSignType getType() {
        return type;
    }

}
