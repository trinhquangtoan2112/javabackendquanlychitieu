package com.data.toan.util.EnumRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TypeValidator implements ConstraintValidator<TypeEnumCheck, Enum<?>> {

    private Pattern pattern;

    @Override
    public void initialize(TypeEnumCheck enumPattern) {
        try {
            pattern = Pattern.compile(enumPattern.regexp());
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext arg1) {
        if (value == null) {
            return true;
        }
        System.out.println(value + " 8888");
        // can name vi value la mot gia tri enum ko phai la string value o sysout thi co
        // the hien ra man hinh
        Matcher m = pattern.matcher(value.name());
        return m.matches();
    }

}
