# Commands for building docker image for Social Network Project

## Go to target folder
{root}\web.social.network\target

## Create container image
docker build -t social-network -f ../../notForBuild/Dockerfile .

## Start container('docker' profile will be used)
docker run -d --name=social-network --link mysql:mysql -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=docker" social-network

## Stop container
docker stop social-network