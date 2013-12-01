package net.hogedriven.backpaper0.javaeeadventcalendar2013;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import net.hogedriven.backpaper0.javaeeadventcalendar2013.UserId;

import org.junit.Before;
import org.junit.Test;

public class DomainTypeTest {

    @DomainType
    public UserId userId;

    private Validator validator;

    @Test
    public void 文字数_10文字で数字とアルファベットはおk() throws Exception {
        userId = UserId.fromString("ABCDE67890");
        Iterator<ConstraintViolation<DomainTypeTest>> it = validator.validate(
                this).iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void 文字数_11文字はアカン() throws Exception {
        userId = UserId.fromString("1234567890x");
        Iterator<ConstraintViolation<DomainTypeTest>> it = validator.validate(
                this).iterator();
        assertThat(it.next().getMessage(), is("10文字以下でｵﾅｼｬｽ"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void 使用可能文字_ひらがなはアカン() throws Exception {
        userId = UserId.fromString("あ");
        Iterator<ConstraintViolation<DomainTypeTest>> it = validator.validate(
                this).iterator();
        assertThat(it.next().getMessage(), is("アルファベットと数字でよろろ"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void 空文字列はおk() throws Exception {
        userId = UserId.fromString("");
        Iterator<ConstraintViolation<DomainTypeTest>> it = validator.validate(
                this).iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void nullはおk() throws Exception {
        userId = null;
        Iterator<ConstraintViolation<DomainTypeTest>> it = validator.validate(
                this).iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
