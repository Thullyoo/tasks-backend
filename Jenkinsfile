pipeline{
  agent any
    stages{
      stage ('Build Backend'){
        steps{
          bat 'mvn clean package -DskipTests=true'
        }
      }
      stage ('Unit Tests'){
        steps{
          bat 'mvn test'
        }
      }
      stage ('Sonar Analysis'){
        enviroment{
          scannerHome = tool 'SONAR_SCANNER'
        }
        steps{
          bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBackend -Dsonar.host.url=http://localhost:9000 -Dsonar.login=eca4e48880c8d2c7aec0b4a64b7a3f419f3aaffd -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
        }
      }
    }
}

