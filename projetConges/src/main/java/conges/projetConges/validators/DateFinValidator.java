package conges.projetConges.validators;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import conges.projetConges.entities.Conge;

public class DateFinValidator implements ConstraintValidator<DateFin, LocalDate>{

	private Conge conge;
	
	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		return (value != null) ? value.isAfter(conge.getDateDebut()) : true;
	}

}
