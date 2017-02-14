
org.springframework.cloud.contract.spec.Contract.make {
  
  priority 1
  
  request {
    method 'POST'
    url '/'
    body(
      name: 'Artem',
      email: 'mail@example.com'
    )
    headers {
      contentType(applicationJson())
    }
  }
  response {
    status 201
    body(
      email: 'mail@example.com',
      name: 'Artem',
      uuid: $(producer(regex(uuid())))
    )
    headers {
      contentType(applicationJson())
    }
  }
}
