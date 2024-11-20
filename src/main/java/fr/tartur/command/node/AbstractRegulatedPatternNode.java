package fr.tartur.command.node;

import fr.tartur.duplispot.command.CommandContext;
import fr.tartur.duplispot.command.CommandNode;
import fr.tartur.duplispot.command.CommandNodeType;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractRegulatedPatternNode<T> implements IPatternNode<T> {

    private final CommandNodeType type;
    private final Predicate<String> predicate;
    private final Function<String, T> mapper;

    protected AbstractRegulatedPatternNode(CommandNodeType type, Predicate<String> predicate, Function<String, T> mapper) {
        this.type = type;
        this.predicate = predicate;
        this.mapper = mapper;
    }

    @Override
    public Optional<CommandNode<T>> match(CommandContext context) {
        return this.predicate.test(context.argument())
                ? Optional.of(new CommandNode<>(this.type, this.mapper.apply(context.argument())))
                : Optional.empty();
    }

    @Override
    public List<String> complete(CommandContext context) {
        return List.of();
    }
}
