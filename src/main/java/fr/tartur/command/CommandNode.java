package fr.tartur.command;

public record CommandNode<T>(CommandNodeType type, T value) {

}
