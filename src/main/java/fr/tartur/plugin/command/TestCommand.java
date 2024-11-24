package fr.tartur.plugin.command;

import fr.tartur.tfl.command.AbstractCommand;
import fr.tartur.tfl.command.CommandStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TestCommand extends AbstractCommand {

    public TestCommand() {
        super("test");
    }

    @Override
    protected boolean run(@NotNull CommandSender sender, CommandStack stack) {
        return false;
    }

}
