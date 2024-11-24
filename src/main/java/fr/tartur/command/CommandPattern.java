package fr.tartur.command;

import fr.tartur.command.node.IPatternNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommandPattern {

    private final Map<Integer, IPatternNode<?>> pattern;

    public CommandPattern(Map<Integer, IPatternNode<?>> pattern) {
        this.pattern = pattern;
    }

    public CommandPattern() {
        this(new HashMap<>());
    }

    public Optional<? extends CommandNode<?>> match(CommandContext context) {
        final IPatternNode<?> pattern = this.pattern.get(context.position());

        if (pattern != null) {
            final var node = pattern.match(context);

            if (node.isPresent()) {
                return node;
            }
        }

        return Optional.empty();
    }

    public List<String> complete(CommandContext context) {
        final IPatternNode<?> pattern = this.pattern.get(context.position());

        if (pattern != null) {
            return pattern.complete(context);
        }

        return List.of();
    }

}
