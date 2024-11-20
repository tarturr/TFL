package fr.tartur.command;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractCommand implements TabExecutor {

    @Getter
    private final String name;
    private final int position;
    private final Set<AbstractCommand> subCommands;
    private final CommandPattern pattern;

    private CommandStack stack;

    public AbstractCommand(String name, int position, Set<AbstractCommand> subCommands, CommandPattern pattern) {
        this.name = name;
        this.position = position;
        this.subCommands = subCommands;
        this.pattern = pattern;
    }

    protected abstract boolean run(@NotNull CommandSender sender, CommandStack stack);

    @Override
    public final boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final CommandStack stack = new CommandStack(this);

        if (args.length >= 1) {
            for (int i = 0; i < args.length; ++i) {
                final AbstractCommand executor = stack.getExecutor();
                final CommandNode<?> node = this.pattern.match(new CommandContext(executor, args[i], executor.getRelativeArgumentPosition(i)));
                stack.add(node);
            }
        }

        this.stack = null;
        return stack.getExecutor().run(sender, stack);
    }

    @Override
    public final @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.stack == null) {
            this.stack = new CommandStack(this);
        }

        // TODO: Finish the tab complete implementation.
        return List.of();
    }

    public Optional<AbstractCommand> getSubCommand(String name, int position) {
        return this.subCommands.stream()
                .filter(command -> command.name.equalsIgnoreCase(name) && command.position == position)
                .findFirst();
    }

    public List<AbstractCommand> getPartialSubCommands(String argument, int position) {
        return this.subCommands.stream()
                .filter(command -> command.name.startsWith(argument) && command.position == position)
                .toList();
    }

    public int getRelativeArgumentPosition(int position) {
        return position - 1 - this.position;
    }

}
