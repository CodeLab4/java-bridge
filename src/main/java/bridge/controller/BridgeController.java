package bridge.controller;

import bridge.model.BridgeGame;
import bridge.model.BridgeMaker;
import bridge.model.CompareBridge;
import bridge.validator.Validator;
import bridge.view.BridgeConstant;
import bridge.view.InputView;
import bridge.view.OutputView;
import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class BridgeController {

    public BridgeController() {
    }

    public int gameCount;
    private boolean gameProgress = true;
    public int moveIndex;
    private String result = "실패";
    private StringBuilder sbUp;
    private StringBuilder sbDown;

    InputView inputView;
    OutputView outputView;
    BridgeGame bridgeGame;
    BridgeMaker bridgeMaker;
    CompareBridge compareBridge;
    Validator validator;


    public BridgeController(InputView inputView, OutputView outputView, BridgeGame bridgeGame, BridgeMaker bridgeMaker,
                            CompareBridge compareBridge, Validator validator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeGame = bridgeGame;
        this.bridgeMaker = bridgeMaker;
        this.compareBridge = compareBridge;
        this.validator = validator;
    }

    public void gameStart() {
        sbUp = new StringBuilder();
        sbDown = new StringBuilder();
        gameCount = 1;
        String moveUpDown = null;
        String strBridgeSize = null;

        int bridgeSize = 0; // 다리 길이
        List<String> randomBridge; // 랜덤한 다리 생성, makeBridge 에서 초기화 진행

        // 다리 길이 입력, 예외 발생 시 계속 돌아가도록
        outputView.printStartGame();

//        bridgeSize = inputView.readBridgeSize();
        boolean checkString = true;
        while (checkString) {
            outputView.printPutBridgeSize();
            strBridgeSize = Console.readLine();
            checkString = validator.validateInteger(strBridgeSize, checkString);
        }
        bridgeSize = Integer.parseInt(strBridgeSize);
        validator.validateBridgeSize(bridgeSize);

        // 예외 숫자 말고 입력 시 발생, 공백 시 발생

        // 브릿지 생성하고
        randomBridge = bridgeMaker.makeBridge(bridgeSize);
        // 생성된 브릿지 출력, 나중에 삭제할 것
        System.out.println(randomBridge);

        while (gameProgress) {

            for (int i = 0; i < randomBridge.size(); i++) {

                moveUpDown = selectMoveUpDown();
                bridgeGame.move(moveIndex); // 한칸 이동

                compareUp(moveUpDown, randomBridge, i);
                compareDown(moveUpDown, randomBridge, i);
                outputView.printMap(sbUp, sbDown);

                if (sbUp.toString().contains("X") || sbDown.toString().contains("X")) {
                    gameProgress = bridgeGame.retry(gameProgress, sbUp, sbDown);
                    i = -1;
                    moveIndex = 0;
                }

                if (!gameProgress) {
                    i = randomBridge.size();
                }

                if (moveIndex == randomBridge.size()) {
                    result = "성공";
                    gameProgress = false;
                }

            }

//            outputView.printMap(sbUp, sbDown);
        }

        outputView.printResultMap(sbUp, sbDown);
        outputView.printResult(result, gameCount);
    }


    private String selectMoveUpDown() {
        boolean checkUpDown = true;
        String moveInput = null;
        while (checkUpDown) {
            outputView.printSelectUpDown();
            moveInput = inputView.readMoving();
            validator.validateUpDown(moveInput);
            if ((moveInput.equals(BridgeConstant.CapitalU.getValue()) ||
                    moveInput.equals(BridgeConstant.CapitalD.getValue()))) {
                checkUpDown = false;
            }
        }
        return moveInput;
    }

    private boolean selectGameRetry(boolean checkGameCommand) {
        while (checkGameCommand) {
            outputView.printSelectGameRestart();
            String gameCommand = inputView.readGameCommand();
            validator.validateReadCommand(gameCommand);
            if (gameCommand.equals("Q")) {
                return checkGameCommand = false;
            }
            break;
        }
        return checkGameCommand;
    }

    private void compareUp(String input, List<String> randomBridge, int i) {
        if (input.equals("U")) {
            sbDown.append("|   ");
            if (input.equals(randomBridge.get(i))) {
                sbUp.append("| O ");
                moveIndex++;
            }
            if (!input.equals(randomBridge.get(i))) {
                sbUp.append("| X ");
            }
        }
    }

    private void compareDown(String input, List<String> randomBridge, int i) {
        if (input.equals("D")) {
            sbUp.append("|   ");
            if (input.equals(randomBridge.get(i))) {
                sbDown.append("| O ");
                moveIndex++;
            }
            if (!input.equals(randomBridge.get(i))) {
                sbDown.append("| X ");
            }
        }
    }

    public void validateInteger(String input) {
        while (true) {
            try {
                Integer.parseInt(input);
                break;
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(BridgeConstant.Error.getValue());
            }
        }
    }

}
