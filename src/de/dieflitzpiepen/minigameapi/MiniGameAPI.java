/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dieflitzpiepen.minigameapi;

import de.dieflitzpiepen.minigameapi.commands.ExampleCommand;
import de.dieflitzpiepen.minigameapi.listener.ExampleListener;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Tammo
 */
public class MiniGameAPI extends JavaPlugin{
    @Override
    public void onEnable(){
        System.out.println("[MiniGameAPI] wird aktiviert...");
        registerListeners();
        this.getCommand("example").setExecutor(new ExampleCommand(this));
        this.getCommand("test").setExecutor(new ExampleCommand(this));
        
    }
    @Override
    public void onDisable(){
        System.out.println("[MiniGameAPI] wird deaktiviert...");
    }
    
    void registerListeners(){
        this.EL = new ExampleListener(this);
    }
    
    void broadcastStuff(){
        final String[] str = {"§cHallo", "§bDas ist ein automatisierter Rundruf" ,"§aGrün", "&7Grau", "§bDie Flitzpiepen"};
        final Random r = new Random();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){ 

            @Override
            public void run() {
                Bukkit.broadcastMessage(str[r.nextInt(str.length)]);//Zufällige Nachrcht aus dem Array broadcasten
            }
            
        }, 0L /*Verzögerung*/, 2*60*20L);//wird alle 2 Minuten wiederholt
    }
    private ExampleListener EL;
}
