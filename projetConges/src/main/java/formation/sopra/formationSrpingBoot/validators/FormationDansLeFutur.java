package formation.sopra.formationSrpingBoot.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = FormationDansLeFuturValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormationDansLeFutur {
	String message() default "la formation doit avoir une date dans le futur";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
