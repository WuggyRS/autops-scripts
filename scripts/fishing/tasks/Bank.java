package scripts.fishing.tasks;

import framework.taskscript.Task;
import main.hooks.filters.AutoInventory;
import main.hooks.wrappers.AutoItem;
import main.hooks.wrappers.AutoNpc;
import main.hooks.wrappers.AutoObject;
import net.runelite.api.Point;
import net.runelite.api.coords.WorldPoint;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Bank extends Task {
    private static final WorldPoint BANK_TILE = new WorldPoint(2809, 3441,0);

    private static final Point[] BANK_PATH = {
            new Point(2851,3429), new Point(2845,3433), new Point(2840, 3432),
            new Point(2836, 3434), new Point(2828, 3436), new Point(2820, 3436),
            new Point(2816, 3436), new Point(2810, 3436), new Point(2809, 3441)
    };

    @Override
    public boolean validate() {
        return auto.getInventory().inventoryFull();
    }

    private void depositAllExcept(String... names) {
        AutoInventory inventory = auto.getInventory().populate();

        // Cache the already deposited items
        HashSet<String> itemsDeposited = new HashSet<>();

        for (AutoItem i : inventory.getList()) {
            String itemName = i.getName();

            // Ignore the already deposited items
            if (Arrays.asList(names).contains(itemName)) continue;

            if (!itemsDeposited.contains(itemName)) {
                auto.updateStatus("Depositing " + i.getName());
                i.click("Deposit-All");
                auto.sleep(250, 400);

                itemsDeposited.add(itemName);
            }
        }
    }

    @Override
    public void execute() {
        // Enable run if not already enabled
        if (!auto.getContext().runActive() && auto.getContext().getRunEnergy() > 60) {
            auto.getContext().toggleRun();

            return;
        }

        // Walk to bank if not near it
        if (auto.getPlayers().getLocal().getLocation().distanceTo(BANK_TILE) > 3) {
            auto.updateStatus("Walking to bank");
            auto.getPathing().walkPath(BANK_PATH);

            return;
        }

        AutoObject bankBooth = auto.getObjects().populate().filter("Bank booth").nextNearest();

        if (bankBooth == null) return;

        // Open bank
        if (!auto.getBank().bankOpen()) {
            bankBooth.click("Bank");
            auto.sleepCondition(() -> auto.getBank().bankOpen(), 1200);

            return;
        }

        // Deposit everything except the harpoon
        depositAllExcept("Harpoon");

        // Close the bank
        if (auto.getBank().bankOpen())
            auto.getBank().closeBank();
    }
}
