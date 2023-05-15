# Treafik 설정 배포
## K3D treafik 설정으로 cluster 설정
```
k3d cluster create my-cluster --agents 1 -p 80:80@loadbalancer -p 443:443@loadbalancer -p 6379:6379@loadbalancer
```
* treafik disable 설정 방법
```
k3d cluster create my-cluster --agents 1 -p 80:80@loadbalancer -p 443:443@loadbalancer -p 6379:6379@loadbalancer --k3s-arg "--disable=traefik@server:0"
```