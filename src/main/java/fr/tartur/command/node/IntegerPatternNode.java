package fr.tartur.command.node;

import fr.tartur.duplispot.command.CommandNodeType;

public class IntegerPatternNode extends AbstractNumberPatternNode<Integer> {

    public IntegerPatternNode() {
        super(CommandNodeType.INTEGER, Integer::parseInt);
    }

}
