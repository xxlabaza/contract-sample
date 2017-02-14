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
package com.xxlabaza.test.contract.users;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.xxlabaza.test.contract.users.account.Account;
import java.util.Map;
import java.util.stream.Stream;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 12.02.2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public abstract class BaseMockMvcTest {

    @Autowired
    private WebApplicationContext context;

    @Resource
    private Map<String, Account> accountRepository;

    @Before
    public void setup () {
        accountRepository.clear();
        Stream.of(
                new Account("ba375b14-3fbc-4fec-a4cd-412a8a967077", "artem@example.com", "Artem"),
                new Account("75f75c8b-f644-491b-9814-bb051d86305f", "liza@example.com", "Liza"),
                new Account("ca7a2536-556b-4289-ada8-42636f5d325b", "milada@example.com", "Milada")
        ).forEach(it -> accountRepository.put(it.getUuid(), it));

        RestAssuredMockMvc.webAppContextSetup(context);
    }
}
