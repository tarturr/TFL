package fr.tartur.command.node;

import fr.tartur.duplispot.command.AbstractCommand;
import fr.tartur.duplispot.command.CommandContext;
import fr.tartur.duplispot.command.CommandNode;
import fr.tartur.duplispot.command.CommandNodeType;

import java.util.List;
import java.util.Optional;

public class CommandPatternNode implements IPatternNode<AbstractCommand> {

    @Override
    public Optional<CommandNode<AbstractCommand>> match(CommandContext context) {
        final Optional<AbstractCommand> foundSubCommand = context.command().getSubCommand(context.argument(), context.position());
        return foundSubCommand.map(subCommand -> new CommandNode<>(CommandNodeType.COMMAND, subCommand));
    }

    @Override
    public List<String> complete(CommandContext context) {
        return context.command().getName().startsWith(context.argument())
                ? List.of(context.command().getName())
                : List.of();
    }

}
