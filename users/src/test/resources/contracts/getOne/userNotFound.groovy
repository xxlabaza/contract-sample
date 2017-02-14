
org.springframework.cloud.contract.spec.Contract.make {
  request {
    method 'GET'
    url value(consumer(regex('/' + uuid())), producer('/c406afe6-1405-4273-b6ea-6973f5f3d32b'))
  }
  response {
    status 404
  }
}
