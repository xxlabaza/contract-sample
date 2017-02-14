
org.springframework.cloud.contract.spec.Contract.make {
  
  request {
    method 'POST'
    url '/?email=no_name@example.com'
  }
  response {
    status 400
    body(
      errors: [
        [
          field: 'name',
          message: 'may not be empty',
          rejectedValue: null
        ]
      ],
      message: 'Validation failed, 1 error(s)'
    )
    headers {
      contentType(applicationJson())
    }
  }
}
