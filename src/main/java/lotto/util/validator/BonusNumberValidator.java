package lotto.util.validator;

public class BonusNumberValidator extends NumberValidator{

    private static final int MIN = 1;
    private static final int MAX = 45;

    @Override
    public void validate(String input) {
        validateNotBlank(input);
        validateNumeric(input);
        int bonusNumber = parseAndValidateRange(input);
        validateRange(bonusNumber, MIN, MAX);
    }
}
