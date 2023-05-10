## SIMPLE-API
간단한 String 형식의 API
### 적용
1. Cluster 생성
```
k3d cluster create my-cluster --agents 1 -p 80:80@loadbalancer -p 443:443@loadbalancer -p 6379:6379@loadbalancer --k3s-arg "--disable=traefik@server:0"
```
2. 네임스페이스 생성
```
k create ns api
```
3. skaffold 배포
```
skaffold dev -p local -t v0.0.7  
```
4. 포트포워딩
```
kubectl port-forward svc/simple-api-svc 8082:8080 -n api
```
