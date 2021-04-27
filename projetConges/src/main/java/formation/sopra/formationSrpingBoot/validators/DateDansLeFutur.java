package formation.sopra.formationSrpingBoot.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateDansLeFuturValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateDansLeFutur {
	String message() default "la formation doit avoir une date dans le futur";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
