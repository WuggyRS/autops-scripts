package scripts.fishing;

import framework.taskscript.TaskScript;
import main.hooks.autops.Category;
import main.hooks.autops.ChatMessage;

import main.hooks.autops.Paintable;
import scripts.fishing.tasks.*;

import java.awt.*;

public class Fishing extends TaskScript {
    @Override
    public void onStart() {
        add(
                new Fish(), new Bank()
        );
    }

    @Override
    public Category scriptDefinitions() {
        Category cat = new Category();

        cat.setAuthor("Wuggy");
        cat.setTitle("Wuggy Fisher");
        cat.setDescription("Fishes and banks");

        return cat;
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onChatMessage(ChatMessage chatMessage) {

    }

    @Override
    public void paint(Graphics g) {
        int x = 20, y = 35;

        g.drawString("Wuggy Fisher", x, y+=15);
    }
}
