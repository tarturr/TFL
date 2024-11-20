package fr.tartur.command.node;

import fr.tartur.duplispot.command.CommandNodeType;

import java.util.function.Function;

public abstract class AbstractNumberPatternNode<T> extends AbstractRegulatedPatternNode<T> {

    protected AbstractNumberPatternNode(CommandNodeType type, Function<String, T> mapper) {
        super(type, argument -> {
            try {
                mapper.apply(argument);
                return true;
            } catch (Exception e) {
                return false;
            }
        }, mapper);
    }

}
