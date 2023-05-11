## SIMPLE-API
간단한 String 형식의 API

### 적용
1. Cluster 생성
```
## traefik 설정을 빼로 생성한다.
k3d cluster create my-cluster --agents 1 -p 80:80@loadbalancer -p 443:443@loadbalancer -p 6379:6379@loadbalancer

## traefik 설정
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

4. Helm Nginx Ingress 설치
```
helm upgrade --install ingress-nginx ingress-nginx/ingress-nginx --namespace ingress-nginx --create-namespace
```

5. 포트포워딩
* 위에서 Helm Ngix Ingress 설치 시에는 포트포워딩이 필요는 없다. 단지 참고만 해라
```
kubectl port-forward svc/simple-api-svc 8082:8080 -n api
```
