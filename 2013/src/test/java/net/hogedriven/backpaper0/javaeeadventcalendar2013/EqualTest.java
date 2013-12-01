package net.hogedriven.backpaper0.javaeeadventcalendar2013;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;

public class EqualTest {

    private Validator validator;

    private String first;

    private String second;

    @Equal(message = "違う値はアカン")
    public Tuple getValue() {
        return new Tuple(first, second);
    }

    @Test
    public void 同じ値はおk() throws Exception {
        first = "a";
        second = "a";
        Iterator<ConstraintViolation<EqualTest>> it = validator.validate(this)
                .iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void 違う値はアカン() throws Exception {
        first = "a";
        second = "b";
        Iterator<ConstraintViolation<EqualTest>> it = validator.validate(this)
                .iterator();
        assertThat(it.next().getMessage(), is("違う値はアカン"));
        assertThat(it.hasNext(), is(false));
    }

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
