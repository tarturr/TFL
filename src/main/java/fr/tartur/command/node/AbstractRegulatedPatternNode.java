package fr.tartur.command.node;

import fr.tartur.command.CommandContext;
import fr.tartur.command.CommandNode;
import fr.tartur.command.CommandNodeType;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRegulatedPatternNode<T> implements IPatternNode<T> {

    private final CommandNodeType type;

    protected AbstractRegulatedPatternNode(CommandNodeType type) {
        this.type = type;
    }

    protected abstract boolean match(String argument);
    protected abstract T map(String argument);

    @Override
    public final Optional<CommandNode<T>> match(CommandContext context) {
        return this.match(context.argument())
                ? Optional.of(new CommandNode<>(this.type, this.map(context.argument())))
                : Optional.empty();
    }

    @Override
    public List<String> complete(CommandContext context) {
        return List.of();
    }

}
