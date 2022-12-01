package bridge.view;

public enum BridgeConstant {

    StartBridgeGame("다리 건너기 게임을 시작합니다."),
    InputBridgeSize("다리 길이를 입력해주세요"),
    SelectBridgeStep("이동할 칸을 선택해주세요."),
    SelectGameRestart("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)"),
    GameResult("최종 게임 결과"),
    GamePass("성공"),
    GameFail("실패"),
    TryGameCount("총 시도한 횟수"),
    CapitalU("U"),
    CapitalD("D"),
    CapitalR("R"),
    CapitalQ("Q"),
    BridgeStart("["),
    BridgeEnd("]"),
    BridgeStick("|"),
    BridgeSpace("   "),
    BridgeSuccess(" O "),
    BridgeFail(" X ");

    private final String value;

    BridgeConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
