package bridge.controller;

import bridge.model.BridgeGame;
import bridge.model.BridgeMaker;
import bridge.model.CompareBridge;
import bridge.validator.Validator;
import bridge.view.BridgeConstant;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

public class BridgeController {

    public int gameCount = 1;
    private boolean gameProgress = true;  // R, Q 구분하는 boolean
    public int moveIndex;                 // 사용자가 몇 번째 칸에 있는지 확인
    private String result = BridgeConstant.GameFail.getValue();
    private StringBuilder sbUp = new StringBuilder();
    private StringBuilder sbDown = new StringBuilder();
    int bridgeSize = 0; // 다리 길이
    boolean checkString = true;   // input 값이 문자열인지 확인, 정수가 들어와야 false 로 변환
    String moveUpDown = null;
    String stringBridgeSize = null; // 검증하기 위해 String 으로 먼저 입력받는다.
    boolean checkLength = true;

    InputView inputView;
    OutputView outputView;
    BridgeGame bridgeGame;
    BridgeMaker bridgeMaker;
    CompareBridge compareBridge;
    Validator validator;

    public BridgeController() {
    }
    public BridgeController(InputView inputView, OutputView outputView, BridgeGame bridgeGame, BridgeMaker bridgeMaker,
                            CompareBridge compareBridge, Validator validator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeGame = bridgeGame;
        this.bridgeMaker = bridgeMaker;
        this.compareBridge = compareBridge;
        this.validator = validator;
    }

    // 메인에서 실행되는 메서드
    public void gameStart() {

        List<String> randomBridge; // 랜덤한 다리 생성, makeBridge 에서 초기화 진행

        // 다리 길이 입력, 예외 발생 시 계속 돌아가도록
        outputView.printStartGame();

        stringBridgeSize = checkString(stringBridgeSize);


        // 3-20 사이인거 확인하는 메서드
        bridgeSize = validator.validateINTEGERLENGTH(stringBridgeSize);

//        while(checkLength) {
//            bridgeSize = Integer.parseInt(strBridgeSize);
//            checkLength = validator.validateBridgeSize(bridgeSize, checkLength);
//            if(checkLength = true){
//                strBridgeSize = Console.readLine();
//            }
//        }

        // 한 번에 두 개 체크...?

//        bridgeSize = Integer.parseInt(stringBridgeSize);
//        validator.validateBridgeSize(bridgeSize);

        // 브릿지 생성하고
        randomBridge = bridgeMaker.makeBridge(bridgeSize);

        System.out.println(randomBridge);  // 생성된 브릿지 출력, 나중에 삭제할 것

        while (gameProgress) {

            for (int i = 0; i < randomBridge.size(); i++) {

                moveUpDown = selectMoveUpDown();
                bridgeGame.move(moveIndex); // 한칸 이동

                compareUp(moveUpDown, randomBridge, i);
                compareDown(moveUpDown, randomBridge, i);
                outputView.printMap(sbUp, sbDown);

                if (sbUp.toString().contains("X") || sbDown.toString().contains("X")) {
                    gameProgress = bridgeGame.retry(gameProgress, sbUp, sbDown);
                    i = -1; // Retry 할 때 i가 ++ 되기 때문에 -1 로 초기화 시켜주어야 한다.
                    moveIndex = 0;   // 틀리면 사용자 위치 0에서 재시작
                    // R 입력 시 gameProgress = true 이기 때문에 게임카운트 1 더해줌
                    if(gameProgress){
                        gameCount++;
                    }
                }
                if (!gameProgress) {
                    i = randomBridge.size(); // Q 입력 시 for 문 종료시키기 위해 i 값 변경
                }
                if (moveIndex == randomBridge.size()) { // 사용자 위치가 다리의 길이와 같아진다면, 결과를 성공으로 바꾸고, while 문 탈출
                    result = BridgeConstant.GamePass.getValue();
                    gameProgress = false;
                }
            }

        }

        // 결과 출력하는 메서드로 묶기엔 parameter 너무 많고, 무의미한 메서드화? 아니면 결과 출력하는 기능 중, 다리와 결과를 출력하는 기능으로 분류??
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

//    private boolean selectGameRetry(boolean checkGameCommand) {
//        while (checkGameCommand) {
//            outputView.printSelectGameRestart();
//            String gameCommand = inputView.readGameCommand();
//            validator.validateReadCommand(gameCommand);
//            if (gameCommand.equals("Q")) {
//                return checkGameCommand = false;
//            }
//            break;
//        }
//        return checkGameCommand;
//    }

    // CompareBridge 클래스에 있어야 하지만 stringBuilder 두 개가 한번에 변환되어야 하다보니 컨트롤러 클래스에 위치함,
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

    public String checkString(String stringBridgeSize) {
        while (checkString) { // 문자열 입력 시 재입력 계속받음 , String 반환하도록 메서드 작성해보기
            outputView.printPutBridgeSize();
            stringBridgeSize = inputView.readStrBridgeSize();
            checkString = validator.validateInteger(stringBridgeSize, checkString);
        }

        return stringBridgeSize;
    }

}
