/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dieflitzpiepen.minigameapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
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

    List<Location> spawns = new ArrayList<Location>();
    List<String> players = new ArrayList<String>();

    public boolean voting = true;

    int arena = 1;
    int maxplayers = 24;

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

    void teleportAll() {
        getSpawns(this.arena);
        int nr = 0;
        for (String str : players) {
            World w = spawns.get(nr).getWorld();
            w.loadChunk(spawns.get(nr).getChunk());
            Bukkit.getPlayer(str).teleport(spawns.get(nr));
            nr++;
        }
    }

    void getSpawns(int arena) {
        World w = Bukkit.getWorld(plugin.locs.getString("arena" + arena + ".world"));
        for (int i = 1; i <= maxplayers; i++) {
            double x = plugin.locs.getDouble("arena" + arena + ".spawns." + (i) + ".x");
            double y = plugin.locs.getDouble("arena" + arena + ".spawns." + (i) + ".y");
            double z = plugin.locs.getDouble("arena" + arena + ".spawns." + (i) + ".z");
            float yaw = (float) plugin.locs.getDouble("arena" + arena + ".spawns." + (i) + ".yaw");
            float pitch = (float) plugin.locs.getDouble("arena" + arena + ".spawns." + (i) + ".pitch");

            spawns.add(new Location(w, x, y, z, yaw, pitch));
        }
    }

    public void addSpawn(int arena, int spawn, Location loc) {
        plugin.locs.set("arena" + arena + ".spawns." + spawn + ".x", loc.getX());
        plugin.locs.set("arena" + arena + ".spawns." + spawn + ".y", loc.getY());
        plugin.locs.set("arena" + arena + ".spawns." + spawn + ".z", loc.getZ());
        plugin.locs.set("arena" + arena + ".spawns." + spawn + ".yaw", loc.getYaw());
        plugin.locs.set("arena" + arena + ".spawns." + spawn + ".pitch", loc.getPitch());

        try {
            plugin.locs.save(plugin.locFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLobbySpawn(Location loc) {
        plugin.locs.set("lobby.world", loc.getWorld().getName());
        plugin.locs.set("lobby.spawn.x", loc.getX());
        plugin.locs.set("lobby.spawn.y", loc.getY());
        plugin.locs.set("lobby.spawn.z", loc.getZ());
        plugin.locs.set("lobby.spawn.yaw", loc.getYaw());
        plugin.locs.set("lobby.spawn.pitch", loc.getPitch());
        try {
            plugin.locs.save(plugin.locFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Location getLobbySpawn() {
        double x = plugin.locs.getDouble("lobby.spawn.x");
        double y = plugin.locs.getDouble("lobby.spawn.y");
        double z = plugin.locs.getDouble("lobby.spawn.z");
        float yaw = (float) plugin.locs.getDouble("lobby.spawn.yaw");
        float pitch = (float) plugin.locs.getDouble("lobby.spawn.pitch");

        return new Location(Bukkit.getWorld(plugin.locs.getString("lobby.world")), x, y, z, yaw, pitch);
    }

    public void setMaxPlayers(int i) {
        this.maxplayers = i;
    }

    public int getMaxPlayers() {
        return maxplayers;
    }

}
