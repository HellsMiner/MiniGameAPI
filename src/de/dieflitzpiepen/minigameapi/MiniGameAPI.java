/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dieflitzpiepen.minigameapi;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Tammo
 */
public class MiniGameAPI extends JavaPlugin{
    @Override
    public void onEnable(){
        System.out.println("[MiniGameAPI] wird aktiviert...");
    }
    @Override
    public void onDisable(){
        System.out.println("[MiniGameAPI] wird deaktiviert...");
    }
}
