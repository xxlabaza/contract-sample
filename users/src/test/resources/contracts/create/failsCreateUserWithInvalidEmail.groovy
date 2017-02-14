
org.springframework.cloud.contract.spec.Contract.make {
  request {
    method 'POST'
    url '/'
    body(
      email: 'invalid_mail.com',
      name: 'Artem'
    )
    headers {
      contentType(applicationJson())
    }
  }
  response {
    status 400
    body(
      errors: [
        [
          field: 'email',
          message: 'not a well-formed email address',
          rejectedValue: 'invalid_mail.com'
        ]
      ],
      message: 'Validation failed, 1 error(s)'
    )
    headers {
      contentType(applicationJson())
    }
  }
}
