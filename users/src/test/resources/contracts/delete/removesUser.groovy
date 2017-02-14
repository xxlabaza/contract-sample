
org.springframework.cloud.contract.spec.Contract.make {
  request {
    method 'DELETE'
    url '/ba375b14-3fbc-4fec-a4cd-412a8a967077'
  }
  response {
    status 200
    body(
      email: 'artem@example.com',
      name: 'Artem',
      uuid: 'ba375b14-3fbc-4fec-a4cd-412a8a967077'
    )
    headers {
      contentType(applicationJson())
    }
  }
}
