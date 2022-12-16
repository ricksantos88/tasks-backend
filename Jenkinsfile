pipeline {
    agent any
    stages {
        stage('Build backend') {
            steps {
                sh 'clean package -DskipTests=true'
            }
        }
    }
}