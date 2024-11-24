package fr.tartur.command;

import java.util.List;
import java.util.Stack;

public class CommandStack extends Stack<CommandNode<?>> {

    public CommandStack(AbstractCommand rootCommand) {
        super.add(new CommandNode<>(CommandNodeType.COMMAND, rootCommand));
    }

    public AbstractCommand getExecutor() {
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
            throw new IllegalStateException("Violated the invariant class: CommandStack must always have a command " +
                    "at its base");
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
