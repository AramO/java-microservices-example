#!/bin/sh
echo ************************************
echo Starting the replica Set
echo ************************************

sleep 10 | echo Sleeping 10

mongo --host mongoserver0:27017 mongoSetup.js


sleep 10 | echo Sleeping 10

mongo --host `mongo mongoserver0:27017 --quiet --eval "db.isMaster()['primary']"` dbSetup.js
