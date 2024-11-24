package fr.tartur.command;

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

    private CommandStack stack;

    public AbstractCommand(String name, int position, Set<AbstractCommand> subCommands, CommandPattern pattern) {
        this.name = name;
        this.position = position;
        this.subCommands = subCommands;
        this.pattern = pattern;
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
        final CommandStack stack = this.stack;
        this.stack = null;

        // TODO: Maybe implement a CommandPattern#fullMatch(CommandStack stack) to check if the command should be
        //  executed or if an error message should be sent.

        return stack.getExecutor().run(sender, stack);
    }

    @Override
    public final List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (this.stack == null) {
            this.stack = new CommandStack(this);
        }

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
        } else if (size == this.stack.size()) {
            this.stack.pop();
        }

        return executor.getPattern().complete(context);
    }

}
