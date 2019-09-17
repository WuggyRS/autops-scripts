package scripts.boneburyer;

import framework.taskscript.TaskScript;
import main.hooks.autops.Category;
import main.hooks.autops.ChatMessage;
import scripts.boneburyer.tasks.Bank;
import scripts.boneburyer.tasks.BuryBones;

public class BoneBuryer extends TaskScript {
    @Override
    public void onStart() {
        add(
                new Bank(), new BuryBones()
        );
    }

    @Override
    public Category scriptDefinitions() {
        Category cat = new Category();

        cat.setTitle("Wuggy Bone Buryer");
        cat.setDescription("Burys normal Bones for Prayer experience");
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
}
