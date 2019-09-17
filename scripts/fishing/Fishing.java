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

        cat.setTitle("Wuggy Fisher");
        cat.setDescription("Fishes shark and banks");
        cat.setVersion(1.0);
        cat.setAuthor("Wuggy");
        cat.setDiscord("Wuggy#0394");

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
        int x = 10, y = 310;

        g.drawString("Wuggy Fisher", x, y);
    }
}
