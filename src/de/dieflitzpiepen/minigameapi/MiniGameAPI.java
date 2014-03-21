/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dieflitzpiepen.minigameapi;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Tammo
 */
public class MiniGameAPI extends JavaPlugin {

    static String MySQL_Host = "";
    static String MySQL_DB = "";
    static String MySQL_User = "";
    static String MySQL_Pass = "";

    File sqlFile = new File("plugins/MiniGameAPI", "MySQL.yml");
    FileConfiguration sql = YamlConfiguration.loadConfiguration(sqlFile);

    @Override
    public void onEnable() {
        System.out.println("[MiniGameAPI] wird aktiviert...");

        if (!sql.contains("Host")) {
            sql.set("Host", "MySQLHost");
            sql.set("Database", "MySQLDataBase");
            sql.set("User", "MySQLUser");
            sql.set("Passwort", "MySQLPasswort");
            try {
                sql.save(sqlFile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("[Hub] MySQL.yml erstellt.");
        }
        MySQL_Host = sql.getString("Host");
        MySQL_DB = sql.getString("Database");
        MySQL_User = sql.getString("User");
        MySQL_Pass = sql.getString("Passwort");

        registerListeners();
        

    }

    @Override
    public void onDisable() {
        System.out.println("[MiniGameAPI] wird deaktiviert...");
    }

    void registerListeners() {
        
    }

    void broadcastStuff() {
        final String[] str = {"§cHallo", "§bDas ist ein automatisierter Rundruf", "§aGrün", "&7Grau", "§bDie Flitzpiepen"};
        final Random r = new Random();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {
                Bukkit.broadcastMessage(str[r.nextInt(str.length)]);//Zufällige Nachrcht aus dem Array broadcasten
            }

        }, 0L /*Verzögerung*/, 2 * 60 * 20L);//wird alle 2 Minuten wiederholt
    }
}
