#!/bin/bash
echo "1"
kubectl create -f namespace.yml
echo "2"
kubectl create -f pv.yml

echo "3"
curl https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get > install-helm.sh
echo "4"
chmod +x install-helm.sh
echo "5"
./install-helm.sh

echo "6"
kubectl -n kube-system create serviceaccount tiller
echo "7"
kubectl create clusterrolebinding tiller --clusterrole cluster-admin --serviceaccount=kube-system:tiller
echo "8"
helm init --service-account tiller
echo "9"
helm repo add incubator https://charts.helm.sh/incubator

echo "10"
curl https://raw.githubusercontent.com/helm/charts/master/incubator/kafka/values.yaml > values.yml
echo "11"
sleep 10s
helm install --name kafka-demo --namespace kafka incubator/kafka -f values.yml --debug

echo "12"
kubectl create -f initialclient.yml

echo "13"
sleep 10s
kubectl -n kafka exec -ti initialclient -- ./bin/kafka-topics.sh --zookeeper kafka-demo-zookeeper:2181 --topic consumer-hooks-pagamentos --create --partitions 5 --replication-factor 3

echo "14"
helm status kafka-demo