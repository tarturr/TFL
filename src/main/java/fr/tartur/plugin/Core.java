package fr.tartur.plugin;

import fr.tartur.plugin.command.TestCommand;
import fr.tartur.tfl.command.CommandLoader;
import fr.tartur.plugin.command.TestIntCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandLoader.builder()
                .command(new TestCommand())
                .command(new TestIntCommand())
                .plugin(this)
                .build()
                .register();

        this.getLogger().info("Plugin successfully loaded! Have a nice day! ^^");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin unloaded! Bye");
    }

}
