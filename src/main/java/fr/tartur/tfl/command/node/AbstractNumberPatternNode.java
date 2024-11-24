package fr.tartur.tfl.command.node;

import fr.tartur.tfl.command.CommandNodeType;

public abstract class AbstractNumberPatternNode<T> extends AbstractRegulatedPatternNode<T> {

    protected AbstractNumberPatternNode(CommandNodeType type) {
        super(type);
    }

    @Override
    protected boolean match(String argument) {
        try {
            this.map(argument);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

}
