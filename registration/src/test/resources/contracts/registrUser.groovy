
org.springframework.cloud.contract.spec.Contract.make {
  
  priority 1
  
  request {
    method 'POST'
    url '/?name=Bob&email=bob@example.com'
  }
  response {
    status 201
    body(
      email: 'bob@example.com',
      name: 'Bob',
      uuid: $(producer(regex(uuid())))
    )
    headers {
      contentType(applicationJson())
    }
  }
}
