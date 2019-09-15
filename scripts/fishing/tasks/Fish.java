package scripts.fishing.tasks;

import framework.taskscript.Task;
import main.hooks.filters.AutoNpcs;
import main.hooks.wrappers.AutoNpc;
import main.robot.api.ClientContext;
import net.runelite.api.Point;
import net.runelite.api.coords.WorldPoint;

import java.util.List;

public class Fish extends Task {
    private static final int SHARK_SPOT = 1520;

    private static final WorldPoint FISHING_SPOT = new WorldPoint(2842, 3432, 0);

    private static final Point[] PATH_TO_FISH = {
            new Point(2811, 3436), new Point(2819, 3436), new Point(2823, 3436),
            new Point(2828, 3436), new Point(2834, 3435), new Point(2838, 3432)
    };

    @Override
    public boolean validate() {
        return !auto.getInventory().inventoryFull()
                && !auto.getPlayers().getLocal().isAnimating();
    }

    @Override
    public void execute() {
        // Enable run if not already enabled
        if (!auto.getContext().runActive() && auto.getContext().getRunEnergy() > 60) {
            auto.getContext().toggleRun();

            return;
        }

        // Walk to fishing area
        if (auto.getPlayers().getLocal().getLocation().distanceTo(FISHING_SPOT) > 16) {
            auto.getPathing().walkPath(PATH_TO_FISH);

            return;
        }

        AutoNpc spot = auto.getNpcs().populate().filter(SHARK_SPOT).nextNearest();

        // Walk closer to the fishing spot if not visible
        if (!spot.visibleOnScreen()) {
            auto.getPathing().walkToCoord(spot.getNpc().getWorldLocation());
            auto.sleep(600, 700);

            return;
        }

        auto.updateStatus("Clicking fishing spot");
        spot.click("Harpoon");
        auto.sleepCondition(() -> auto.getPlayers().getLocal().isAnimating(), 4000);
    }
}
