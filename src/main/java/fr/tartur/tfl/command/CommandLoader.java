package fr.tartur.tfl.command;

import lombok.Builder;
import lombok.Singular;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandLoader {

    private final JavaPlugin plugin;
    private final Map<String, CommandGroup> commands;

    @Builder
    public CommandLoader(JavaPlugin plugin, @Singular List<AbstractCommand> commands) {
        this.plugin = plugin;
        this.commands = this.dispatch(commands);
    }

    public void register() {
        for (final CommandGroup command : this.commands.values()) {
            final PluginCommand pluginCommand = this.plugin.getCommand(command.getRoot());

            if (pluginCommand == null) {
                throw new IllegalArgumentException("Tried to register a command without adding it in plugin.yml");
            }

            pluginCommand.setExecutor(command);
        }
    }

    private Map<String, CommandGroup> dispatch(List<AbstractCommand> commands) {
        final Map<String, CommandGroup.CommandGroupBuilder> groups = new HashMap<>();

        for (final AbstractCommand command : commands) {
            final String name = command.getName();

            if (groups.containsKey(name)) {
                groups.get(command.getName()).command(command);
            } else {
                groups.put(command.getName(), CommandGroup.builder().command(command));
            }
        }

        return groups.keySet().stream().collect(Collectors.toMap(
                name -> name,
                name -> groups.get(name).build()
        ));
    }

}
