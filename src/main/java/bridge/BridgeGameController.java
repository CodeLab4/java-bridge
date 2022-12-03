package bridge;

import java.util.List;

public class BridgeGameController {

    BridgeGame bridgeGame;
    InputView inputView;
    OutputView outputView;
    BridgeRandomNumberGenerator bridgeRandomNumberGenerator;
    int gameAttempts = 0;

    BridgeGameController(BridgeGame bridgeGame, InputView inputView, OutputView outputView) {
        this.bridgeGame = bridgeGame;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void printInitialMessage() {
        outputView.printInitialMessage();
    }

    public void printInputBridgeLength() {
        outputView.printInputBridgeLength();
    }

    public void inputBridgeLength() {
        bridgeGame.bridgeLength = inputView.readBridgeSize();
    }

    public void printUserDecideWhich() {
        outputView.printUserDecideUpDown();
    }

    public void createBridge() {
        bridgeRandomNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeMaker bridgeMaker = new BridgeMaker(bridgeRandomNumberGenerator);
        bridgeGame.bridge = bridgeMaker.makeBridge(bridgeGame.bridgeLength);
    }

    public void initialSetting() {
        printInitialMessage();
        printInputBridgeLength();
        inputBridgeLength();
        createBridge();
    }

    public boolean checkCompleteIncomplete(StringBuilder upper, StringBuilder under) {
        if (upper.toString().contains("X") || under.toString().contains("X")) {
            return false;
        }
        return true;
    }

    public void gameStart() {
        do {
            gameAttempts++;
            bridgeGame.createUpperUnderStringBuilder();
            boolean reachEnd = false;
            boolean whetherRightWrong = true;
            for (int i = 0; i < bridgeGame.bridgeLength && whetherRightWrong; i++) {
                printUserDecideWhich();
                String choice = inputView.readMoving();
                whetherRightWrong = bridgeGame.move(choice, bridgeGame.bridge.get(i));
                outputView.printMap(bridgeGame.bridgeUpperUnder.get(0), bridgeGame.bridgeUpperUnder.get(1));
                bridgeGame.appendPartition(bridgeGame.bridgeUpperUnder.get(0), bridgeGame.bridgeUpperUnder.get(1));
                if (i == bridgeGame.bridgeLength - 1) {
                    reachEnd = true;
                }
            }
            if (reachEnd && whetherRightWrong) {
                break;
            }
            outputView.printWhetherContinue();
        } while (bridgeGame.retry(inputView.readGameCommand()));
    }

    public void endGame() {
        outputView.printResult(bridgeGame.bridgeUpperUnder, checkCompleteIncomplete(bridgeGame.bridgeUpperUnder.get(0), bridgeGame.bridgeUpperUnder.get(1)), gameAttempts);
    }
}
