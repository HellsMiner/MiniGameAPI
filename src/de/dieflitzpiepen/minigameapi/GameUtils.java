/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dieflitzpiepen.minigameapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 *
 * @author Tammo
 */
public class GameUtils {

    private static GameUtils instance;

    private MiniGameAPI plugin;

    public GameUtils(MiniGameAPI plugin) {
        this.plugin = plugin;
        instance = GameUtils.this;
    }

    public static GameUtils getInstance() {
        return instance;
    }

    public Map<String, Integer> vote = new HashMap<String, Integer>();
    public ArrayList<String> voted = new ArrayList<String>();
    
    public boolean voting = true;
    
    //mapvoting scoreboard
    public void updateScoreboard() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard mapvote = manager.getNewScoreboard();
            String m1 = plugin.locs.getString("arena1.name");
            String m2 = plugin.locs.getString("arena2.name");
            String m3 = plugin.locs.getString("arena3.name");
            String m1s = "";
            String m2s = "";
            String m3s = "";
            if (m1.length() > 16) {
                m1s = m1.substring(15);
                m1 = m1.substring(0, 15);
            }
            if (m2.length() > 16) {
                m2s = m2.substring(15);
                m2 = m2.substring(0, 15);
            }
            if (m3.length() > 16) {
                m3s = m3.substring(15);
                m3 = m3.substring(0, 15);
            }
            Team team1 = mapvote.registerNewTeam(m1);
            Team team2 = mapvote.registerNewTeam(m2);
            Team team3 = mapvote.registerNewTeam(m3);

            team1.addPlayer(Bukkit.getOfflinePlayer(m1));
            team2.addPlayer(Bukkit.getOfflinePlayer(m2));
            team3.addPlayer(Bukkit.getOfflinePlayer(m3));
            team1.setPrefix(ChatColor.GREEN + "1. ");
            team2.setPrefix(ChatColor.GREEN + "2. ");
            team3.setPrefix(ChatColor.GREEN + "3. ");
            team1.setSuffix(m1s);
            team2.setSuffix(m2s);
            team3.setSuffix(m3s);

            Objective objective = mapvote.registerNewObjective("test", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName("§6§lVoting");
            Score map1 = objective.getScore(Bukkit.getOfflinePlayer(m1));
            Score map2 = objective.getScore(Bukkit.getOfflinePlayer(m2));
            Score map3 = objective.getScore(Bukkit.getOfflinePlayer(m3));
            map1.setScore(vote.get("1"));
            map2.setScore(vote.get("2"));
            map3.setScore(vote.get("3"));

            p.setScoreboard(manager.getNewScoreboard());
            p.setScoreboard(mapvote);
        }
    }

}
