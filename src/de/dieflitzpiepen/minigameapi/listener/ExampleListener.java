/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dieflitzpiepen.minigameapi.listener;

import de.dieflitzpiepen.minigameapi.MiniGameAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author Tammo
 */
public class ExampleListener implements Listener{
    private MiniGameAPI plugin;

    public ExampleListener(MiniGameAPI plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onJoin/*beliebiger Name*/(PlayerJoinEvent e){
        if(e.getPlayer().isOp()){
            e.setJoinMessage("§4Admin " + e.getPlayer().getName() + " §7ist jetzt online.");
        } else{
            e.setJoinMessage("§a" + e.getPlayer().getName() + " §7ist jetzt online.");
        }
        
        
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        if(e.getPlayer().isOp()){
            e.setQuitMessage("§4Admin " + e.getPlayer().getName() + " §7ist jetzt offline.");
        } else{
            e.setQuitMessage("§a" + e.getPlayer().getName() + " §7ist jetzt offline.");
        }
        
        
    }
}
