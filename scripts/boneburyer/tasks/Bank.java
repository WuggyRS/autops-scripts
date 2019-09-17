package scripts.boneburyer.tasks;

import framework.taskscript.Task;
import main.hooks.wrappers.AutoItem;
import main.hooks.wrappers.AutoObject;

public class Bank extends Task {
    @Override
    public boolean validate() {
        return auto.getInventory().populate().filter("Bones").population() == 0;
    }

    @Override
    public void execute() {
        auto.updateStatus("Banking");

        if (!auto.getBank().bankOpen()) {
            AutoObject bank = auto.getObjects().populate().filter("Bank booth", "Grand Exchange booth").next();

            bank.click("Bank");
            auto.sleepCondition(() -> auto.getBank().bankOpen(), 1000);

            return;
        }

        AutoItem bones = auto.getBank().populate().filter("Bones").next();

        if (bones == null) {
             auto.updateStatus("Out of bones");

             return;
        }

        bones.click("Withdraw-All");
        auto.sleep(600, 700);

        if (auto.getBank().bankOpen())
            auto.getBank().closeBank();
    }
}
