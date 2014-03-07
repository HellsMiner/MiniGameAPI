/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dieflitzpiepen.minigameapi.commands;

import de.dieflitzpiepen.minigameapi.MiniGameAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Tammo
 */
public class ExampleCommand implements CommandExecutor{
    private MiniGameAPI plugin;

    public ExampleCommand(MiniGameAPI plugin) {
        this.plugin = plugin;
    }
  
    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("example")){
            sender.sendMessage("§cDas ist ein rotes Beispiel.");
            if(sender instanceof Player){
                Player p = (Player) sender;
                p.sendMessage("§bDu bist ein Spieler.");
                //mit p.*** kann man seeehr viel machen :D hier ein paar Beispiele
                p.setOp(true);
                p.setAllowFlight(true);
                p.setFlying(true);
                p.chat("ich bin dumm und habe /example gemacht");//Spieler chattet ...
                Bukkit.broadcastMessage("§a" + p.getName() + " hat /example gemacht");//broadcast...
                p.setHealth(20.0);
                p.getInventory().addItem(new ItemStack(Material.BAKED_POTATO, 2)); //gebe dem Spieler 2 Baked Potato :D
                p.setDisplayName("dummerPlayer"); //seinen DisplayName umändern
                p.setLevel(1);//level setzen^^
                
            }
            return true;//--> Command ist hier zuende
        }
        if(cmd.getName().equalsIgnoreCase("test")){
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){

                @Override
                public void run() {
                    sender.sendMessage("10 Sekunden später...");//hier verwendete Variablen brauchen den modifier final s.o. (final CommandSender sender)
                }
                
                    }, 10 * 20L /* = 10 * 20 Ticks. --> 20 Ticks = 1 Sekunde.*/);
            return true;
        }
        
        return false;
    }
    
}
