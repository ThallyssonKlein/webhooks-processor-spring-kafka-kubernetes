#!/bin/bash
echo "---------------"
kubectl create -f paymentsapi-pod.yml
kubectl create -f paymentsapi-service.yml