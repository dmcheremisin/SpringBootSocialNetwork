docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=password -d mysql 
docker exec -it mysql bash -l
mysql -uroot -ppassword