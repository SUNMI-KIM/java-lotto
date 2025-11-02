package lotto.util.exception;

public enum ErrorMessage {
    EMPTY_INPUT("[ERROR] 입력은 비어있으면 안됩니다."),

    INVALID_UNIT("[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다."),
    INVALID_NUMBER_FORMAT("[ERROR] 구입 금액은 숫자여야 합니다."),
    INVALID_RANGE("[ERROR] 입력이 범위에 맞지 않습니다."),

    INVALID_WINNING_NUMBER_FORMAT("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자 형식이어야 합니다."),
    DUPLICATE_WINNING_NUMBER("[ERROR] 당첨 번호는 중복될 수 없습니다."),
    INVALID_WINNING_NUMBER_SIZE("[ERROR] 당첨 번호는 6개의 숫자로 이루어져야 합니다."),

    DUPLICATE_BONUS_NUMBER("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
