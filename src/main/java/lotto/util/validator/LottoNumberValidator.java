package lotto.util.validator;

public class LottoNumberValidator {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private final NumberValidator numberValidator;

    public LottoNumberValidator(NumberValidator numberValidator) {
        this.numberValidator = numberValidator;
    }

    public void validate(int bonusNumber) {
        numberValidator.validateRange(bonusNumber, MIN, MAX);
    }
}
