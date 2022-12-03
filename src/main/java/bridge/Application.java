package bridge;

import bridge.controller.BridgeController;
import bridge.model.BridgeGame;
import bridge.model.BridgeMaker;
import bridge.model.BridgeRandomNumberGenerator;
import bridge.model.CompareBridge;
import bridge.validator.Validator;
import bridge.view.InputView;
import bridge.view.OutputView;

public class Application {

    public static void main(String[] args) {
        BridgeController bridgeController = new BridgeController(new InputView(), new OutputView(),
                new BridgeGame(), new BridgeMaker(new BridgeRandomNumberGenerator()), new CompareBridge(), new Validator());
        bridgeController.gameStart();

    }
}
