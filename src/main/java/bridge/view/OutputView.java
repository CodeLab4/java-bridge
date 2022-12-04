package bridge.view;

import bridge.model.BridgeGame;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 * - 제공된 `OutputView` 클래스를 활용해 구현해야 한다.
 * - `OutputView`의 패키지는 변경할 수 있다.
 * - `OutputView`의 메서드의 이름은 변경할 수 없고, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.
 * - 값 출력을 위해 필요한 메서드를 추가할 수 있다.
 */
public class OutputView {
    public static StringBuilder topBridge = new StringBuilder();
    public static StringBuilder bottomBridge = new StringBuilder();
    public static StringJoiner topJoiner;
    public static StringJoiner bottomJoiner;

    public void printStart() {
        System.out.println("다리 건너기 게임을 시작합니다.");
    }

    public void printInputSize() {
        System.out.println("다리의 길이를 입력해주세요.");
    }

    public void printInputMoving() {
        System.out.println();
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */

    // int size
    public void printMap(boolean moveSuccess, String moving) {


        if (moveSuccess) {
            if (moving.equals("U")) {
                topBridge.append("O");
                bottomBridge.append(" ");
            } else {
                topBridge.append(" ");
                bottomBridge.append("O");
            }
        } else {
            if (moving.equals("U")) {
                topBridge.append("X");
                bottomBridge.append(" ");
            } else {
                topBridge.append(" ");
                bottomBridge.append("X");
            }
        }
        String[] topTemp = topBridge.toString().split("");
        String[] bottomTemp = bottomBridge.toString().split("");

        topJoiner = new StringJoiner(" | ", "[ ", " ]");
        bottomJoiner = new StringJoiner(" | ", "[ ", " ]");

        for (int i = 0; i < BridgeGame.index+1; i++) {
          topJoiner.add(topTemp[i]);
          bottomJoiner.add(bottomTemp[i]);
        }

        System.out.println(topJoiner);
        System.out.println(bottomJoiner);
    }

    public void printRestart() {
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(boolean moveSuccess, int gameCount) {
        System.out.println("최종 게임 결과");

        System.out.println(topJoiner);
        System.out.println(bottomJoiner);

        if (moveSuccess) {
            System.out.println("게임 성공 여부: 성공");
        } else {
            System.out.println("게임 성공 여부: 실패");
        }
        System.out.println("총 시도한 횟수: " + gameCount);
    }
}
