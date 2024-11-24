package fr.tartur.command.node;

import fr.tartur.command.AbstractCommand;
import fr.tartur.command.CommandContext;
import fr.tartur.command.CommandNode;
import fr.tartur.command.CommandNodeType;

import java.util.List;
import java.util.Optional;

public class CommandPatternNode implements IPatternNode<AbstractCommand> {

    @Override
    public Optional<CommandNode<AbstractCommand>> match(CommandContext context) {
        final Optional<AbstractCommand> foundSubCommand = context.command().getSubCommands().stream()
                .filter(command -> command.getName().equalsIgnoreCase(context.argument())
                        && command.getPosition() == context.position())
                .findFirst();

        return foundSubCommand.map(subCommand -> new CommandNode<>(CommandNodeType.COMMAND, subCommand));
    }

    @Override
    public List<String> complete(CommandContext context) {
        return context.command().getSubCommands().stream()
                .filter(command -> command.getName().startsWith(context.argument())
                        && command.getPosition() == context.position())
                .map(AbstractCommand::getName)
                .toList();
    }

}
