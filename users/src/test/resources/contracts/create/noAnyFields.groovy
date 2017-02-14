
org.springframework.cloud.contract.spec.Contract.make {
    
  request {
    method 'POST'
    url '/'
    headers {
      contentType(applicationJson())
    }
  }
  response {
    status 400
  }
}
