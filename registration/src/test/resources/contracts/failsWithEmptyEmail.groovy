
org.springframework.cloud.contract.spec.Contract.make {
  
  request {
    method 'POST'
    url '/?name=Ron'
  }
  response {
    status 400
    body(
      errors: [
        [
          field: 'email',
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
