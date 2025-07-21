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
        environment{
          scannerHome = tool 'SONAR_SCANNER'
        }
        steps{
          withSonarQubeEnv('SONAR_LOCAL'){
            bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBackend -Dsonar.host.url=http://localhost:9000 -Dsonar.login=eca4e48880c8d2c7aec0b4a64b7a3f419f3aaffd -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
          }
        }
      }
      stage ('Quality Gate'){
        steps{
          sleep(25)
          timeout(time: 1, unit: 'MINUTES'){
            waitForQualityGate abortPipeline: true  
          }
        }
      }
      stage ('Deploy Backend'){
        steps{
          deploy adapters: [tomcat8(alternativeDeploymentContext: '', credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', onFailure: false, war: 'target/tasks-backend.war'
        }
      }
    }
}

