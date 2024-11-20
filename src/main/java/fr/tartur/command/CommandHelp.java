package fr.tartur.command;

import lombok.Builder;
import lombok.Singular;
import net.kyori.adventure.text.Component;

import java.util.Map;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

@Builder
public class CommandHelp {

    private final String name;
    @Singular(value = "definition")
    private final Map<String, String> argumentDefinitions;

    public CommandHelp(String name, Map<String, String> argumentDefinitions) {
        this.name = name;
        this.argumentDefinitions = argumentDefinitions;
    }

    public Component getAsComponent() {
        Component component = text("Liste des commandes disponibles :").color(AQUA);

        for (final String argument : this.argumentDefinitions.keySet()) {
            final String description = this.argumentDefinitions.get(argument);

            component = component.appendNewline().append(text("- ").color(WHITE))
                    .append(text("/" + this.name + " " + argument).color(YELLOW))
                    .append(text(" : ").color(WHITE))
                    .append(text(description.endsWith(".") ? description : description + ".").color(GRAY));
        }

        return component;
    }

}
