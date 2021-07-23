#!/bin/bash
sudo docker stop $(sudo docker ps -q -a)
sudo docker rm $(sudo docker container ls -a -q);sudo docker rmi $(sudo docker images -a -q)
sudo docker stop $(sudo docker ps -q)