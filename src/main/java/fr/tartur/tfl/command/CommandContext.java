package fr.tartur.tfl.command;

public record CommandContext(AbstractCommand command, String argument, int position) {
}
