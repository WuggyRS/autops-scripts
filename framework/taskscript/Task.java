package framework.taskscript;

import main.robot.api.ClientContext;

public abstract class Task {
    public ClientContext auto;

    public abstract boolean validate();
    public abstract void execute();

    public void setContext(ClientContext c) {
        auto = c;
    }
}
