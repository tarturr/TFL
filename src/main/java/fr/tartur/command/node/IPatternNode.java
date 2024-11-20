package fr.tartur.command.node;

import fr.tartur.duplispot.command.CommandContext;
import fr.tartur.duplispot.command.CommandNode;

import java.util.List;
import java.util.Optional;

public interface IPatternNode<T> {

    Optional<CommandNode<T>> match(CommandContext context);
    List<String> complete(CommandContext context);

}
