package fr.tartur.tfl.command.node;

import fr.tartur.tfl.command.CommandContext;
import fr.tartur.tfl.command.CommandNode;
import fr.tartur.tfl.command.CommandNodeType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class PlayerPatternNode implements IPatternNode<Player> {

    @Override
    public Optional<CommandNode<Player>> match(CommandContext context) {
        return Optional.ofNullable(Bukkit.getPlayerExact(context.argument()))
                .map(player -> new CommandNode<>(CommandNodeType.PLAYER, player));
    }

    @Override
    public List<String> complete(CommandContext context) {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .toList();
    }

}
