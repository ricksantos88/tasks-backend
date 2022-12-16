pipeline {
    agent any
    stages {
        stage('Build backend') {
            steps {
                mvn clean package -DskipTests=true
            }
        }
    }
}