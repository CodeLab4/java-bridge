package bridge;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public void printInitialMessage() {
        System.out.println("다리 건너기 게임을 시작합니다.");
    }

    public void printInputBridgeLength() {
        System.out.println("다리의 길이를 입력해주세요.");
    }

    public void printUserDecideUpDown() {
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
    }

    public void printWhetherContinue() {
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(StringBuilder upper, StringBuilder under) {
        System.out.println("[" + upper + "]");
        System.out.println("[" + under + "]");
    }

    public void printResultMap(StringBuilder upper, StringBuilder under) {
        upper.deleteCharAt(upper.length() - 1);
        under.deleteCharAt(under.length() - 1);
        System.out.println("[" + upper + "]");
        System.out.println("[" + under + "]");
    }

    public void printCompleteIncomplete(boolean flag) {
        System.out.print("게임 성공 여부: ");
        if (flag) {
            System.out.println("성공");
            return;
        }
        System.out.println("실패");
    }

    public void printAttempts(int gameAttempts) {
        System.out.println("총 시도한 횟수: " + gameAttempts);
    }

    public void printFinalGameResult() {
        System.out.println("최종 게임 결과");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(List<StringBuilder> bridgeUpperUnder, boolean flag, int gameAttempts) {
        printFinalGameResult();
        printResultMap(bridgeUpperUnder.get(0), bridgeUpperUnder.get(1));
        printCompleteIncomplete(flag);
        printAttempts(gameAttempts);
    }
}
