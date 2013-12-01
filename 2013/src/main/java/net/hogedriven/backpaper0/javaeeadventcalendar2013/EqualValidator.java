package net.hogedriven.backpaper0.javaeeadventcalendar2013;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualValidator implements ConstraintValidator<Equal, Tuple> {

    @Override
    public void initialize(Equal constraintAnnotation) {
    }

    @Override
    public boolean isValid(Tuple value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        return Objects.equals(value.first, value.second);
    }
}
