package scripts.boneburyer.tasks;

import framework.taskscript.Task;

public class BuryBones extends Task {
    @Override
    public boolean validate() {
        return auto.getInventory().populate().filter("Bones").population() > 0;
    }

    @Override
    public void execute() {
        auto.updateStatus("Burying bones");

        auto.getInventory().populate().filter("Bones").next().click("Bury");
        auto.sleep(400, 550);
    }
}
