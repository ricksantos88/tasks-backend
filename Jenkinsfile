pipeline {
    agent any
    tools { 
      maven 'MAVEN_JENKINS' 
    }
    stages {
        stage('Build backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Deploy backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcat-login', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        stage('API test') {
            steps {
                dir('api-test') {
                    git credentialsId: 'github-login', url: 'https://github.com/ricksantos88/tasks-api-test'
                    bat 'mvn test'
                }
            }
        }
    }
}