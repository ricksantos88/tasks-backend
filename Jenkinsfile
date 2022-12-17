pipeline {
    agent any
    tools { 
      maven 'MAVEN_JENKINS'
      git 'GIT_JENKINS' 
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
                    git branch: 'main', credentialsId: 'github-login', url: 'https://github.com/ricksantos88/tasks-api-test'
                    bat 'mvn test'
                }
            }
        }
        stage('Deploy frontend') {
            steps {
                dir('frontend') {
                    git branch: 'master', credentialsId: 'github-login', url: 'https://github.com/ricksantos88/tasks-frontend'
                    bat 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'tomcat-login', path: '', url: 'http://localhost:8001')], contextPath: 'tasks', war: 'target/tasks.war'
                }
            }
        }
        stage('Deploy Prod') {
            steps {
                bat 'docker-compose build '
                bat 'docker-compose up -d'
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml, api-test/target/surefire-reports/*.xml'
        }
    }
}