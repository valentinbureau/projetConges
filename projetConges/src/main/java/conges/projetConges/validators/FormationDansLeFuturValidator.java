package conges.projetConges.validators;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FormationDansLeFuturValidator implements ConstraintValidator<FormationDansLeFutur, LocalDate> {

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		return (value != null) ? value.isAfter(LocalDate.now()) : true;
	}

}
