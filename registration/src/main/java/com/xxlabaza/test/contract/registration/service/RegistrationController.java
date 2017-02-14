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
package com.xxlabaza.test.contract.registration.service;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 13.02.2017
 */
@RestController
class RegistrationController {

    @Autowired
    private UsersApi usersApi;

    @PostMapping
    @ResponseStatus(CREATED)
    public Map<String, Object> registrate (@Valid User user) {
        if (usersApi.getAll().stream().anyMatch(it -> it.get("email").equals(user.getEmail()))) {
            throw new UserAlreadyExistsException();
        }
        return usersApi.create(user);
    }
}
