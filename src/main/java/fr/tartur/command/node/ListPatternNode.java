package fr.tartur.command.node;

import fr.tartur.duplispot.command.CommandContext;
import fr.tartur.duplispot.command.CommandNode;
import fr.tartur.duplispot.command.CommandNodeType;

import java.util.List;
import java.util.Optional;

public class ListPatternNode implements IPatternNode<String> {

    private final List<String> predefinedValues;

    public ListPatternNode(List<String> predefinedValues) {
        this.predefinedValues = predefinedValues;
    }

    @Override
    public Optional<CommandNode<String>> match(CommandContext context) {
        return this.predefinedValues.stream()
                .filter(value -> value.equalsIgnoreCase(context.argument()))
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
