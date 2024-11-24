package fr.tartur.command;

import lombok.Builder;
import lombok.Singular;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Builder
public class CommandLoader {

    private final JavaPlugin plugin;
    @Singular
    private final List<AbstractCommand> commands;

    public CommandLoader(JavaPlugin plugin, List<AbstractCommand> commands) {
        this.plugin = plugin;
        this.commands = commands;
    }

    public void register() {
        for (final AbstractCommand command : this.commands) {
            final PluginCommand pluginCommand = this.plugin.getCommand(command.getName());

            if (pluginCommand == null) {
                throw new IllegalArgumentException("Tried to register a command without adding it in plugin.yml");
            }

            pluginCommand.setExecutor(command);
        }
    }

}
