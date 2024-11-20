package fr.tartur.command;

import lombok.Getter;

import java.util.Stack;

public class CommandStack {

    private final Stack<AbstractCommand> commands;
    @Getter
    private final Stack<CommandNode<?>> arguments;

    public CommandStack(AbstractCommand rootCommand) {
        this.commands = new Stack<>();
        this.commands.add(rootCommand);
        this.arguments = new Stack<>();
    }

    public void add(CommandNode<?> node) {
        if (node.value() instanceof AbstractCommand command) {
            this.commands.add(command);
        } else {
            this.arguments.add(node);
        }
    }

    public int size() {
        return this.commands.size() + this.arguments.size();
    }

    public AbstractCommand getExecutor() {
        return this.commands.peek();
    }

}
