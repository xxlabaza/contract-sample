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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 12.02.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = NONE,
        properties = {
            "ribbon.eureka.enabled=false",
            "feign.hystrix.enabled=false"
        }
)
@AutoConfigureStubRunner(
        ids = "com.xxlabaza.test.contract:users:+:stubs:9000",
        workOffline = true
)
@DirtiesContext
public class UsersApiTest {

    @Autowired
    private UsersApi api;

    @Test
    public void create () {
        Map<String, Object> response = api.create(new User("Artem", "mail@example.com"));
        // cheks minimal set of fields
        assertFalse(response.get("uuid").toString().isEmpty());
        assertFalse(response.get("name").toString().isEmpty());
        assertFalse(response.get("email").toString().isEmpty());
    }

    @Test
    public void getAll () {
        List<Map<String, Object>> users = api.getAll();
        assertTrue(users.size() > 0);
        assertTrue(users.stream().map(it -> it.get("email")).allMatch(Objects::nonNull));
    }
}
