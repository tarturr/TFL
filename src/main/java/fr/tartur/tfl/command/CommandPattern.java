package fr.tartur.tfl.command;

import fr.tartur.tfl.command.node.CommandPatternNode;
import fr.tartur.tfl.command.node.IPatternNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.*;

public class CommandPattern {

    private final Map<Integer, IPatternNode<?>> patterns;

    @Getter
    @Setter
    private boolean isValid;

    @Builder
    public CommandPattern(@Singular Map<Integer, IPatternNode<?>> patterns) {
        this.patterns = patterns;
        this.isValid = this.patterns.isEmpty();
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

    public void registerSubCommands(AbstractCommand command) {
        this.patterns.put(command.getPosition(), new CommandPatternNode());
    }

}
