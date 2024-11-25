package fr.tartur.tfl.command;

import fr.tartur.tfl.command.node.IPatternNode;
import lombok.Builder;
import lombok.Singular;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Builder
public class CommandPattern {

    @Singular
    private final Map<Integer, IPatternNode<?>> patterns;

    public CommandPattern(Map<Integer, IPatternNode<?>> patterns) {
        this.patterns = patterns;
    }

    public CommandPattern() {
        this(new HashMap<>());
    }

    public Optional<? extends CommandNode<?>> match(CommandContext context) {
        final IPatternNode<?> pattern = this.patterns.get(context.position());

        if (pattern != null) {
            final var node = pattern.match(context);

            if (node.isPresent()) {
                return node;
            }
        }

        return Optional.empty();
    }

    public List<String> complete(CommandContext context) {
        final IPatternNode<?> pattern = this.patterns.get(context.position());

        if (pattern != null) {
            return pattern.complete(context);
        }

        return List.of();
    }

    public boolean isEmpty() {
        return this.patterns.isEmpty();
    }

}
