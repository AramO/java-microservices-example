#!/bin/bash

#https://docs.docker.com/engine/reference/commandline/pull/

#docker container stop registry && docker container rm -v registry

#mkdir docker-registry && cd docker-registry
mkdir auth && cd auth

docker run \
  --entrypoint htpasswd \
  httpd:2 -Bbn testuser testpassword > auth/htpasswd

docker run -d \
  -p 5000:5000 \
  --restart=always \
  --name registry \
  -v "$(pwd)"/auth:/auth \
  -e "REGISTRY_AUTH=htpasswd" \
  -e "REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm" \
  -e REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd \
  registry:2

#curl -u testuser:testpassword -X GET http://178.18.255.102:5000/v2/_catalog