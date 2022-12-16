pipeline {
    agent any
    tools { 
      maven 'MAVEN_JENKINS' 
    }
    stages {
        stage('Build backend') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit tests') {
            step {
                sh 'mvn test'
            }
        }
    }
}