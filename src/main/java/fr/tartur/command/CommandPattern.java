package fr.tartur.command;

import fr.tartur.duplispot.command.node.IPatternNode;

import java.util.List;
import java.util.Map;

public class CommandPattern {

    private final Map<Integer, IPatternNode<?>> pattern;

    public CommandPattern(Map<Integer, IPatternNode<?>> pattern) {
        this.pattern = pattern;
    }

    public CommandNode<?> match(CommandContext context) {
        final IPatternNode<?> pattern = this.pattern.get(context.position());

        if (pattern != null) {
            final var foundNode = pattern.match(context);

            if (foundNode.isPresent()) {
                return foundNode.get();
            }
        }

        return new CommandNode<>(CommandNodeType.DEFAULT, context.argument());
    }

    public List<String> complete(CommandContext context) {
        final IPatternNode<?> pattern = this.pattern.get(context.position());

        if (pattern != null) {
            return pattern.complete(context);
        }

        return List.of();
    }

}
