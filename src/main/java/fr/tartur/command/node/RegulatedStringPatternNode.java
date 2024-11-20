package fr.tartur.command.node;

import fr.tartur.duplispot.command.CommandNodeType;

import java.util.function.Predicate;

public class RegulatedStringPatternNode extends AbstractRegulatedPatternNode<String> {

    public RegulatedStringPatternNode(Predicate<String> predicate) {
        super(CommandNodeType.REGULATED_STRING, predicate, string -> string);
    }

}
