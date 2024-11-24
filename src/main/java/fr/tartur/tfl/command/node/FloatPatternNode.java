package fr.tartur.tfl.command.node;

import fr.tartur.tfl.command.CommandNodeType;

public class FloatPatternNode extends AbstractNumberPatternNode<Float> {

    protected FloatPatternNode() {
        super(CommandNodeType.FLOAT);
    }

    @Override
    protected Float map(String argument) {
        return Float.parseFloat(argument);
    }

}
