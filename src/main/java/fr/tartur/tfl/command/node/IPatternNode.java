package fr.tartur.tfl.command.node;

import fr.tartur.tfl.command.CommandContext;
import fr.tartur.tfl.command.CommandNode;

import java.util.List;
import java.util.Optional;

public interface IPatternNode<T> {

    Optional<CommandNode<T>> match(CommandContext context);
    List<String> complete(CommandContext context);

}
