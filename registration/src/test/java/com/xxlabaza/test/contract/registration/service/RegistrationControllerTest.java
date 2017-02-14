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

import static org.mockito.Mockito.when;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.xxlabaza.test.contract.registration.Main;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 14.02.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public abstract class RegistrationControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UsersApi usersApi;

    @Before
    public void setup () {
        when(usersApi.create(new User("Bob", "bob@example.com"))).thenReturn(
                user("b008076f-cf7e-4816-803d-2fb3368992fb", "Bob", "bob@example.com")
        );

        when(usersApi.getAll()).thenReturn(Arrays.asList(
                user("fd704c1c-ba33-4695-a4ac-8f2de9ce5d0c", "Artem", "mail@example.com"),
                user("8034deef-6538-4d68-bd62-40ea6ffbaa55", "Liza", "liza@example.com"),
                user("6451d665-9c68-4f8d-8058-56ce69c952f7", "Milada", "milada@example.com")
        ));

        RestAssuredMockMvc.webAppContextSetup(context);
    }

    private Map<String, Object> user (String uuid, String name, String email) {
        Map<String, Object> result = new HashMap<>(3, 1.F);
        result.put("uuid", uuid);
        result.put("name", name);
        result.put("email", email);
        return result;
    }
}
