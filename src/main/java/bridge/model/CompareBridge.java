package bridge.model;

import java.util.List;

public class CompareBridge {

    public StringBuilder compareUp(String input, List<String> randomBridge, int i) {
        StringBuilder sbUp = new StringBuilder();
        if(input.equals(randomBridge.get(i))){
            return sbUp.append("| O ");
        }
        if(!input.equals(randomBridge.get(i))){
            return sbUp.append("| X ");
        }

        return sbUp;
    }

    public StringBuilder compareDown(String input, List<String> randomBridge, int i) {
        StringBuilder sbDown = new StringBuilder();

        if(input.equals(randomBridge.get(i))){
            return sbDown.append("| O ");
        }
        if(!input.equals(randomBridge.get(i))){
            return sbDown.append("| X ");
        }

        return sbDown;

    }


    public void compareDown() {

    }

}

