#!/bin/bash

GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'
RED='\033[0;31m'
DOCKER='aramohanyan'

set -e

if [[ $# -eq 0 ]]; then
     echo -e "\n${RED}###############Please mention service name '###################${NC}\n"
     exit 0
fi

printf "\n${BLUE}###############MVN build###################${NC}\n"
mvn clean install -pl "$1"

printf "\n${BLUE}###############Docker login###################${NC}\n"
docker login registry-1.docker.io

printf "\n${BLUE}###############Docker build###################${NC}\n"
docker-compose build --no-cache "$1"

printf "\n${BLUE}###############Docker tag###################${NC}\n"
docker tag "$1" registry-1.docker.io/"$DOCKER"/"$1"

printf "\n${BLUE}###############Docker push###################${NC}\n"
docker push registry-1.docker.io/"$DOCKER"/"$1"

printf "${GREEN}###############Done successfully###################${NC}\n"
