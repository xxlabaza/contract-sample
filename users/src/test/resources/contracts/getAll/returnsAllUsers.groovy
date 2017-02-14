
org.springframework.cloud.contract.spec.Contract.make {
  request {
    method 'GET'
    url '/'
  }
  response {
    status 200
    body(
      [
        [
          email: $(producer(regex(email())), consumer('artem@example.com')),
          name: 'Artem',
          uuid: $(producer(regex(uuid())))
        ],
        [
          email: $(producer(regex(email())), consumer('liza@example.com')),
          name: 'Liza',
          uuid: $(producer(regex(uuid())))
        ],
        [
          email: $(producer(regex(email())), consumer('milada@example.com')),
          name: 'Milada',
          uuid: $(producer(regex(uuid())))
        ]
      ]
    )
    headers {
      contentType(applicationJson())
    }
  }
}
