/*
 * Copyright 2017 Artem Labazin <xxlabaza@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xxlabaza.test.contract.registration.exception;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Optional;
import lombok.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 12.02.2017
 */
@Value
class ValidationError {

    String message;

    @JsonInclude(NON_EMPTY)
    ErrorMessage[] errors;

    ValidationError (BindingResult bindingResult) {
        message = String.format("Validation failed, %d error(s)", bindingResult.getErrorCount());
        errors = Optional.ofNullable(bindingResult.getFieldErrors())
                .map(it -> it.stream().map(ErrorMessage::new).toArray(ErrorMessage[]::new))
                .orElse(new ErrorMessage[0]);
    }

    @Value
    static class ErrorMessage {

        String field;

        Object rejectedValue;

        String message;

        ErrorMessage (FieldError fieldError) {
            field = fieldError.getField();
            rejectedValue = fieldError.getRejectedValue();
            message = fieldError.getDefaultMessage();
        }
    }
}
