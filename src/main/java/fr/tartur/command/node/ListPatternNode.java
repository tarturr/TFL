package fr.tartur.command.node;

import fr.tartur.command.CommandContext;
import fr.tartur.command.CommandNode;
import fr.tartur.command.CommandNodeType;

import java.util.List;
import java.util.Optional;

public class ListPatternNode implements IPatternNode<String> {

    private final List<String> predefinedValues;
    private final boolean ignoreCase;

    public ListPatternNode(List<String> predefinedValues, boolean ignoreCase) {
        this.predefinedValues = predefinedValues;
        this.ignoreCase = ignoreCase;
    }

    @Override
    public Optional<CommandNode<String>> match(CommandContext context) {
        return this.predefinedValues.stream()
                .filter(value -> this.ignoreCase
                        ? value.equalsIgnoreCase(context.argument())
                        : value.equals(context.argument()))
                .findFirst()
                .map(value -> new CommandNode<>(CommandNodeType.DEFAULT, value));
    }

    @Override
    public List<String> complete(CommandContext context) {
        return this.predefinedValues.stream()
                .filter(value -> value.startsWith(context.argument()))
                .toList();
    }

}
