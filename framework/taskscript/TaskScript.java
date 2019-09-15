package framework.taskscript;

import main.hooks.autops.Category;
import main.hooks.autops.ChatMessage;
import main.robot.api.ClientContext;
import main.robot.script.Script;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class TaskScript extends Script {
    private ArrayList<Task> tasks = new ArrayList<>();
    public abstract void onStart();

    @Override
    public void onExecute() {
        onStart();

        for (Task t : tasks) {
            t.setContext(auto);
        }
    }

    @Override
    public void onProcess() {
        for (Task t : tasks) {
            if (t.validate()) {
                t.execute();

                return;
            }
        }
    }

    public void add(Task... t) {
        tasks.addAll(Arrays.asList(t));
    }

    public void removeAll() {
        tasks.clear();
    }
}
