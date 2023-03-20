# simple-api

이 프로젝트 springboot기반  simple-api project입니다.

주로 단순한 서비스 연동, 클러스터에서의 서비스 확인,CICD테스트 등의 용도로 사용됩니다.

빌드 및 실행을 위해 jdk(jdk11이상,jdk17권장)는 미리 설치되어 있어야 하며 
maven wrapper를 통해 다음과 같이 실행하시기 바랍니다.

```bash
#build
./mvnw clean install -DskipTests
#run
./mvnw spring-boot:run
```

## 클러스터에 빌드하고 배포하기

skaffold를 이용하여 -p(profile), -t(version-git tag) 옵션을 적절히 주고 실행하면 해당 클러스터에 디버깅 모드로 배포됩니다.

```bash
# debug mode
skaffold dev -p dev -t 0.0.1 
# build
skaffold build -p dev -t 0.0.1 
# run
skaffold run -p dev -t 0.0.1 
```

클러스터에 요청을 보내어 로그가 정상적으로 들어오는지 확인합니다.

## direnv를 통한 kubeconfig 관리

매번 클러스터를 테스트 혹은 개발용으로 이용할 경우 그 정보를 관리하기가 쉽지 않으므로, 다음과 같이 direnv를 이용하여 디렉토리 단위로 해당k8s설정파일(kubeconfig)을 관리합니다. 

```
# direnv 설치 및 적용
sudo apt install direnv

# 위 명령 전에 ./kubeconfig 파일에 접속을 원하는 클러스터 접속 config를 복사하고
# direnv를 통해 로딩되도록 함. 

## .envrc파일의 내용 
#!/usr/bin/env bash
export KUBECONFIG=./kubeconfig

## 다음과 같이 로딩함
direnv allow
```

## test

vscode에 httpyac 이라는 플러그인을 설치 한 뒤 request.http 파일을 열어
테스트 주소가 맞는지 확인하고 각각의 요청을 테스트한다.