/*
 * MIT License
 *
 * Copyright (C) 2021 - 2023 Alfie Nutley (ANutley)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.anutley.dislink.bukkit;

import com.tcoded.folialib.FoliaLib;
import me.anutley.dislink.common.DisLink;
import org.bukkit.plugin.java.JavaPlugin;

public class DisLinkBukkitLoader extends JavaPlugin {

    private FoliaLib foliaLib;
    private DisLink disLink;

    @Override
    public void onEnable() {
        // Initialize FoliaLib
        this.foliaLib = new FoliaLib(this);

        // Log the platform we're running on for debugging
        getLogger().info("DisLink is running on: " + getPlatformName());

        // Initialize the main DisLink instance
        this.disLink = new DisLink(
                new DisLinkBukkitLogger(getLogger()),
                getDataFolder(),
                foliaLib
        );
    }

    private String getPlatformName() {
        if (foliaLib.isFolia()) {
            return "Folia (Multi-threaded)";
        } else if (foliaLib.isPaper()) {
            return "Paper";
        } else if (foliaLib.isSpigot()) {
            return "Spigot";
        } else {
            return "Unknown Bukkit-based server";
        }
    }

    @Override
    public void onDisable() {
        // Cancel all tasks associated with FoliaLib when the plugin disables
        if (foliaLib != null) {
            foliaLib.getScheduler().cancelAllTasks();
        }

        // Shutdown DisLink
        if (disLink != null) {
            disLink.shutdown();
        }
    }
}