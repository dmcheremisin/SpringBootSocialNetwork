package info.cheremisin.social.network.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	private static final String PASSWORD_PATTERN = "(?=.*[0-9].*)(?=.*[a-zA-Z].*)[0-9a-zA-Z]{6,}";

	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext context) {
		if (password == null) {
			return false;
		}
		return password.matches(PASSWORD_PATTERN);
	}

}