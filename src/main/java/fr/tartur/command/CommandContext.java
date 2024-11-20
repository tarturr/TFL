package fr.tartur.command;

public record CommandContext(AbstractCommand command, String argument, int position) {
}
