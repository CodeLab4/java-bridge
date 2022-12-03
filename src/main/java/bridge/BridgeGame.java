package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    List<String> bridge;
    int bridgeLength;
    List<StringBuilder> bridgeUpperUnder;

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(String userDecision, String answer) {
        if (userDecision.equals(answer)) {
            whenCorrect(bridgeUpperUnder.get(0), bridgeUpperUnder.get(1), userDecision);
            return true;
        }
        whenIncorrect(bridgeUpperUnder.get(0), bridgeUpperUnder.get(1), userDecision);
        return false;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(String retryOrQuit) {
        if (retryOrQuit.equals("Q")) {
            return false;
        }
        return true;
    }

    public void createUpperUnderStringBuilder() {
        bridgeUpperUnder = new ArrayList<>();
        StringBuilder upper = new StringBuilder();
        StringBuilder under = new StringBuilder();
        bridgeUpperUnder.add(upper);
        bridgeUpperUnder.add(under);
    }

    public void appendPartition(StringBuilder upper, StringBuilder under) {
        upper.append("|");
        under.append("|");
    }

    public void printBridge(StringBuilder upper, StringBuilder under) {
        System.out.println("[" + upper + "]");
        System.out.println("[" + under + "]");
    }

    public void whenCorrect(StringBuilder upper, StringBuilder under, String choice) {
        if (choice.equals("U")) {
            upper.append(" O ");
            under.append("   ");
            return;
        }
        upper.append("   ");
        under.append(" O ");
    }

    public void whenIncorrect(StringBuilder upper, StringBuilder under, String choice) {
        if (choice.equals("U")) {
            upper.append(" X ");
            under.append("   ");
            return;
        }
        upper.append("   ");
        under.append(" X ");
    }
}
