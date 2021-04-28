package conges.projetConges.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateFinValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFin {
	String message() default "la date de fin doit arriver après la date de debut";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
