package org.kepennar.sharemouz.backend.model;

import com.google.common.collect.ImmutableList;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class ValidationError {

    private final ImmutableList<String> errors;
    private final String field;

    private ValidationError(String field, String... errors) {
        this.field = checkNotNull(field);
        this.errors = ImmutableList.copyOf(errors);
    }

    public static List<ValidationError> of(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream().map(f -> new
                ValidationError(f.getField(), f.getDefaultMessage())).collect(Collectors.toList());
    }

    public String getField() {
        return field;
    }

    public ImmutableList<String> getErrors() {
        return errors;
    }
}