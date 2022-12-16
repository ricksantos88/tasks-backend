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
                deploy adapters: [tomcat9(credentialsId: 'tomcat-login', path: '', url: 'http://172.19.224.1:8001')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
    }
}