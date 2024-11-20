package fr.tartur.command.node;

import fr.tartur.duplispot.command.CommandNodeType;

public class FloatPatternNode extends AbstractNumberPatternNode<Float> {

    protected FloatPatternNode() {
        super(CommandNodeType.FLOAT, Float::parseFloat);
    }

}
