package fr.tartur.tfl.command;

public record CommandNode<T>(CommandNodeType type, T value) {

}
