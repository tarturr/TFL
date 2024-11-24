package fr.tartur.tfl.command.node;

import fr.tartur.tfl.command.CommandNodeType;

public class IntegerPatternNode extends AbstractNumberPatternNode<Integer> {

    public IntegerPatternNode() {
        super(CommandNodeType.INTEGER);
    }

    @Override
    protected Integer map(String argument) {
        return Integer.parseInt(argument);
    }

}
