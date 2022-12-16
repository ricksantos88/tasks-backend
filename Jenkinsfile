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
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcat-login', path: '', url: 'http://172.20.176.1:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
    }
}