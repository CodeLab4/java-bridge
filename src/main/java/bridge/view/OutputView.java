package bridge.view;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    // 게임을 시작합니다 출력
    public void printStartGame() {
        System.out.println(BridgeConstant.StartBridgeGame.getValue());
    }

    // 다리길이를 입력하세요 출력
    public void printPutBridgeSize() {
        System.out.println(BridgeConstant.InputBridgeSize.getValue());
    }

    // 위아래 선택
    public void printSelectUpDown() {
        System.out.println(BridgeConstant.SelectBridgeStep.getValue());
    }

    // 게임 재시작 여부 입력받기
    public void printSelectGameRestart() {
        System.out.println(BridgeConstant.SelectGameRestart.getValue());
    }
    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(StringBuilder sbUp, StringBuilder sbDown) {
        if(sbUp.charAt(0) == '|'){
            sbUp.deleteCharAt(0);
            sbDown.deleteCharAt(0);

        }
        System.out.println(BridgeConstant.BridgeStart.getValue() + sbUp + BridgeConstant.BridgeEnd.getValue());
        System.out.println(BridgeConstant.BridgeStart.getValue() + sbDown + BridgeConstant.BridgeEnd.getValue());
        System.out.println();
    }


    // 최종 게임 결과 + 결과 맵 출력
    public void printResultMap(StringBuilder sbUp, StringBuilder sbDown) {
        System.out.println(BridgeConstant.GameResult.getValue());
        printMap(sbUp, sbDown);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    // 게임 결과 출력
    public void printResult(String result, int gameCount) {
        System.out.println(BridgeConstant.WhetherGameSuccess.getValue() + result);
        System.out.println(BridgeConstant.TotalGameCount.getValue() + gameCount);
    }

}
