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
package com.xxlabaza.test.contract.users.account;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Artem Labazin <xxlabaza@gmail.com>
 * @since 13.02.2017
 */
@RestController
class AccountsController {

    @Resource
    private Map<String, Account> accountRepository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Account create (@Valid @RequestBody Account account) {
        account.setUuid(UUID.randomUUID().toString());
        accountRepository.put(account.getUuid(), account);
        return account;
    }

    @GetMapping
    public Collection<Account> getAll () {
        return accountRepository.values();
    }

    @GetMapping("/{id}")
    public Account getOne (@PathVariable("id") String id) {
        return Optional.ofNullable(accountRepository.get(id))
                .orElseThrow(AccountNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    public Account delete (@PathVariable("id") String id) {
        return Optional.ofNullable(accountRepository.remove(id))
                .orElseThrow(AccountNotFoundException::new);
    }
}
