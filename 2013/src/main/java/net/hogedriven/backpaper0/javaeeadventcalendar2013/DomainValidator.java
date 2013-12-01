package net.hogedriven.backpaper0.javaeeadventcalendar2013;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DomainValidator implements
        ConstraintValidator<DomainType, WithValidation> {

    @Override
    public void initialize(DomainType constraintAnnotation) {
    }

    @Override
    public boolean isValid(WithValidation value,
            ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        String message = value.validate();
        if (message == null) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();

        return false;
    }
}
