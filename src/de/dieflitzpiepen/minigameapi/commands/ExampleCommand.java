/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dieflitzpiepen.minigameapi.commands;

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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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
                return true;//--> Command ist hier zuende
            }
        }
        
        return false;
    }
    
}
