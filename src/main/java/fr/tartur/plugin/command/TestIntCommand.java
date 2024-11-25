package fr.tartur.plugin.command;

import fr.tartur.tfl.command.AbstractCommand;
import fr.tartur.tfl.command.CommandPattern;
import fr.tartur.tfl.command.CommandStack;
import fr.tartur.tfl.command.node.IntegerPatternNode;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TestIntCommand extends AbstractCommand {

    public TestIntCommand() {
        super("test", CommandPattern.builder()
                .pattern(0, new IntegerPatternNode())
                .build());
    }

    @Override
    protected boolean run(@NotNull CommandSender sender, CommandStack stack) {
        final int value = (Integer) stack.get(0).value();

        sender.sendMessage(value + "² = " + value * value);
        return true;
    }

}
