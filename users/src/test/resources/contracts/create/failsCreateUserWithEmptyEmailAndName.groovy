
org.springframework.cloud.contract.spec.Contract.make {
  request {
    method 'POST'
    url '/'
    body(
      email: '',
      name: ''
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
          message: 'may not be empty',
          rejectedValue: ''
        ],
        [
          field: 'name',
          message: 'may not be empty',
          rejectedValue: ''
        ]
      ],
      message: 'Validation failed, 2 error(s)'
    )
    headers {
      contentType(applicationJson())
    }
  }
}
