package at.blank.coinapi;

import at.blank.coinapi.Utils.Config.MySqlConfig;
import at.blank.coinapi.Utils.MySql.MySql;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Coinapi extends JavaPlugin {

    public static Coinapi instance;
    public static FileConfiguration cfg;
    private static MySql mySQL;


    public static MySql getMySQL() {
        return mySQL;
    }

    public static Coinapi getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        MySqlConfig file = new MySqlConfig();
        file.setStandard();
        file.readData();

        loadConfig();
        Coinapi.cfg = this.getConfig();
        mySQL = new MySql();
        mySQL.connect();

    }

    @Override
    public void onDisable() {
        mySQL.disconnect();
        instance = this;
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
