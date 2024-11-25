package fr.tartur.tfl.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CommandGroup implements TabExecutor {

    @Getter
    private final String root;
    private final List<AbstractCommand> commands;

    @Builder
    public CommandGroup(@Singular List<AbstractCommand> commands) {
        if (commands.isEmpty()) {
            throw new IllegalArgumentException("Trying to create an empty command group");
        }

        this.root = commands.get(0).getName();

        for (final AbstractCommand command : commands) {
            final String name = command.getName();

            if (!name.equals(this.root)) {
                throw new IllegalArgumentException("Could not register '" + this.root + "' command group because a " +
                        "different command with name '" + name + "' has been provided");
            }
        }

        this.commands = commands;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final boolean result = this.commands.stream().anyMatch(
                groupCommand -> groupCommand.onCommand(sender, command, label, args)
        );

        if (!result) {
            sender.sendMessage("Unknown use of the /" + this.getRoot() + " command...");
        }

        return result;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return this.commands.stream()
                .flatMap(groupCommand -> groupCommand.onTabComplete(sender, command, label, args).stream())
                .toList();
    }

}
