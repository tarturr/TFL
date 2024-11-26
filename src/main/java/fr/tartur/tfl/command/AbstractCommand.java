package fr.tartur.tfl.command;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public abstract class AbstractCommand implements TabExecutor {

    private final String name;
    private final int position;
    private final Set<AbstractCommand> subCommands;
    private final CommandPattern pattern;

    private final CommandStack stack;

    public AbstractCommand(String name, int position, Set<AbstractCommand> subCommands, CommandPattern pattern) {
        this.name = name;
        this.position = position;
        this.subCommands = subCommands;
        this.pattern = pattern;
        this.pattern.registerSubCommands(this);

        this.stack = new CommandStack(this);
    }

    public AbstractCommand(String name, Set<AbstractCommand> subCommands, CommandPattern pattern) {
        this(name, 0, subCommands, pattern);
    }

    public AbstractCommand(String name, Set<AbstractCommand> subCommands) {
        this(name, 0, subCommands, new CommandPattern());
    }

    public AbstractCommand(String name, CommandPattern pattern) {
        this(name, 0, new HashSet<>(), pattern);
    }

    public AbstractCommand(String name) {
        this(name, 0, new HashSet<>(), new CommandPattern());
    }

    protected abstract boolean run(@NotNull CommandSender sender, CommandStack stack);

    @Override
    public final boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final AbstractCommand executor = this.stack.getExecutor();

        if (this.pattern.isValid()) {
            final boolean result = executor.run(sender, this.stack);
            this.stack.clear();
            return result;
        } else {
            return false;
        }
    }

    @Override
    public final List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final int size = args.length;

        final AbstractCommand executor = this.stack.getExecutor();
        final int position = size - 1;
        final String argument = args[position];

        final CommandContext context = new CommandContext(executor, argument, position);

        final var node = executor.pattern.match(context);

        if (node.isPresent()) {
            if (size == this.stack.size()) {
                this.stack.pop();
            }

            this.stack.add(node.get());
            this.pattern.setValid(true);
        } else if (size == this.stack.size()) {
            this.stack.pop();
            this.pattern.setValid(false);
        } else if (!argument.isBlank()) {
            this.pattern.setValid(false);
        }

        return executor.getPattern().complete(context);
    }

}
