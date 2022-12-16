pipeline {
    agent any
    tools {
        maven 'Maven 3.8.6'
    }
    stages {
        stage('Build backend') {
            steps {
                sh "mvn clean package -DskipTests=true"
            }
        }
    }
}