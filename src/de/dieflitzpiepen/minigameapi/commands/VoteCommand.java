/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dieflitzpiepen.minigameapi.commands;

import de.dieflitzpiepen.minigameapi.GameUtils;
import de.dieflitzpiepen.minigameapi.MiniGameAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Tammo
 */
public class VoteCommand implements CommandExecutor{
    
    private GameUtils GU;
	private MiniGameAPI plugin;
	
	public VoteCommand(MiniGameAPI plugin){
		this.plugin = plugin;
		this.GU = GameUtils.getInstance();
	}
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if(!(sender instanceof Player)){
			sender.sendMessage("Du musst ein Spieler sein");
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("vote")){
			if(args.length == 0){
				p.sendMessage("§cVerwendung: §b/vote <map>");
				return true;
			}
			
			
			if(!GU.voting){
				p.sendMessage("§cDu kannst jetzt nicht voten");
				return true;
			}
			if(GU.voted.contains(p.getName())){
				p.sendMessage("§cDu hast schon gevotet");
				return true;
			}
			
			if(!args[0].equals("1") && !args[0].equals("2") && !args[0].equals("3")){
				p.sendMessage("§cVerwendung: §b/vote <map>");
				p.sendMessage("§cMap Nummer zwischen 1 und 3");
				return true;
			}
			String map = args[0];
			int curr = GU.vote.get(map) + 1;
			GU.vote.put(map, curr);
			
			GU.updateScoreboard();
			p.sendMessage("§aDu hast für §6" + map + ". " + plugin.locs.getString("arena" + map + ".name") + " §agevotet");
			return true;
		}
		return false;
        
        
    }
    
}
