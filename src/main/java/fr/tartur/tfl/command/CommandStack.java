package fr.tartur.tfl.command;

import java.util.List;
import java.util.Stack;

public class CommandStack extends Stack<CommandNode<?>> {

    private final AbstractCommand rootCommand;

    public CommandStack(AbstractCommand rootCommand) {
        this.rootCommand = rootCommand;
    }

    public AbstractCommand getExecutor() {
        if (super.isEmpty()) {
            return this.rootCommand;
        }

        final Stack<CommandNode<?>> temp = new Stack<>();
        AbstractCommand command = null;

        while (!super.isEmpty()) {
            final CommandNode<?> node = super.pop();
            temp.add(node);

            if (node.value() instanceof final AbstractCommand foundCommand) {
                command = foundCommand;
                break;
            }
        }

        if (command == null) {
            command = rootCommand;
        }

        while (!temp.isEmpty()) {
            super.add(temp.pop());
        }

        return command;
    }

    public List<CommandNode<?>> getArguments() {
        return super.stream()
                .filter(node -> node.type() != CommandNodeType.COMMAND)
                .toList();
    }

}
