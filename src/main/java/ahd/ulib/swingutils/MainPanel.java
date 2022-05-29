package ahd.ulib.swingutils;

import ahd.ulib.utils.api.ConfigBase;
import ahd.ulib.utils.api.StateBase;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainPanel extends JRootPane implements StateBase<String, Container>, ElementBaseContainer, ConfigBase<String, Object> {

    private final Map<String, Container> stateMap;
    private final Map<String, Object> configMap;
    private final Map<String, Object> elements;

    private String currentState;

    public MainPanel() {
        stateMap = new HashMap<>(Map.of(currentState = "initial", getContentPane()));
        configMap = new HashMap<>();
        elements = new HashMap<>();
    }

    @Override
    public Map<String, Object> elements() {
        return elements;
    }

    @Override
    public Map<String, Object> config() {
        return configMap;
    }

    @Override
    public String currentState() {
        return currentState;
    }

    @Override
    public Map<String, Container> stateMap() {
        return stateMap;
    }

    @Override
    public void addState(String key, Container value) {
        stateMap.put(key, value);
    }

    @Override
    public void removeState(String key) {
        if (currentState.equals(key))
            setState("initial");
        stateMap.remove(key);
    }

    @Override
    public void setState(String state) {
        setContentPane(stateMap.get(currentState = state));
        repaint();
        revalidate();
    }
}
