APP_PATH=$4/$(echo $1 | sed -r 's/\./\//g')/$2/$3/$2-$3.war
docker-compose down
rm -rf ./webapps/*
cp $APP_PATH ./webapps/ROOT.war
docker-compose up -d
 