package bridge.controller;

import bridge.model.BridgeGame;
import bridge.model.BridgeMaker;
import bridge.model.BridgeRandomNumberGenerator;
import bridge.view.InputView;
import bridge.view.OutputView;
import bridge.view.OutputView.Message;
import java.util.List;

public class BridgeGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeGame bridgeGame;
    private final BridgeMaker bridgeMaker;
    private String gameFlag = "실패";
    private boolean gameRetryFlag = false;
    private int gameCount = 1;

    public BridgeGameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.bridgeGame = new BridgeGame();
        this.bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
    }

    public void gameStart() {
        Message.GAME_START_MESSAGE.infoMessage();
        Message.GAME_BRIDGE_LENGTH_INPUT_MESSAGE.infoMessage();
    }

    public int bridgeSizeChecker() {
        String inputSize = "";
        boolean sizeFlag = true;
        while (sizeFlag) {
            inputSize = inputView.readBridgeSize();
            if (!(validNumber(inputSize) || validNumberRange(inputSize))) {
                sizeFlag = false;
            }
        }
        return Integer.parseInt(inputSize);
    }
    public void gameProgress(int inputSize) {
        List<String> bridges = bridgeMaker.makeBridge(inputSize);
        while (bridges.size() > bridgeGame.getMoveIndex()) {
            Message.GAME_PROGRESS_MOVE_POSITION_MESSAGE.infoMessage();
            String inputUpDown = bridgeUpDownChecker();
            if (!bridgeGame.compare(bridges, inputUpDown)) {
                outputView.printMap(bridgeGame.getMoveIndex(), inputUpDown, bridges);
                gameRetry(inputSize);
                continue;
            }
            outputView.printMap(bridgeGame.getMoveIndex(), inputUpDown, bridges);
            bridgeGame.move();
        }
    }

    public String bridgeUpDownChecker() {
        String inputUpDown = "";
        boolean upDownFlag = true;
        while (upDownFlag) {
            inputUpDown = inputView.readMoving();
            if (!validUpDown(inputUpDown)) {
                upDownFlag = false;
            }
        }
        return inputUpDown;
    }

    public void gameRetry(int inputSize) {
        Message.GAME_RETRY_MESSAGE.infoMessage();
        String retryInput = retryInputChecker();
        if (bridgeGame.retry(retryInput)) {
            gameCount++;
            outputView.initialization();
            bridgeGame.initialization();
            return;
        }
        bridgeGame.initialization(inputSize);
        gameRetryFlag = true;
    }

    public String retryInputChecker() {
        String inputUpRetry = "";
        boolean upDownFlag = true;
        while (upDownFlag) {
            inputUpRetry = inputView.readGameCommand();
            if (!validRetry(inputUpRetry)) {
                upDownFlag = false;
            }
        }
        return inputUpRetry;
    }

    public void gameResult() {
        if (!gameRetryFlag) {
            gameFlag = "성공";
        }
        Message.GAME_RESULT_HEADER_MESSAGE.infoMessage();
        outputView.printResult(gameFlag, gameCount);
    }

    public boolean validNumber(String input) {
        String compareString = input.replaceAll("[0-9]", "");
        try {
            if (compareString.length() != 0) {
                throw new IllegalArgumentException(Message.ERROR_INPUT_NUMBER_MESSAGE.errorMessage());
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            return true;
        }
        return false;
    }

    public boolean validNumberRange(String input) {
        try {
            if (Integer.parseInt(input) < 3 || Integer.parseInt(input) > 20) {
                throw new IllegalArgumentException(Message.ERROR_INPUT_RANGE_MESSAGE.errorMessage());
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            return true;
        }
        return false;
    }

    public boolean validUpDown(String input) {
        try {
            if (!(input.equals("U") || input.equals("D"))) {
                throw new IllegalArgumentException(Message.ERROR_INPUT_UP_DOWN_MESSAGE.errorMessage());
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            return true;
        }
        return false;
    }

    public boolean validRetry(String input) {
        try {
            if (!(input.equals("R") || input.equals("Q"))) {
                throw new IllegalArgumentException(Message.ERROR_INPUT_RETRY_MESSAGE.errorMessage());
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            return true;
        }
        return false;
    }
}
