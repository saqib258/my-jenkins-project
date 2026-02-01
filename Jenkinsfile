pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/saqib258/my-jenkins-project.git'
            }
        }
        stage('Verify Environment') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
                sh 'docker --version'
            }
       stage('Security Analysis (SonarQube)') {
    steps {
        // withSonarQubeEnv uses the "Name" you gave in Jenkins System settings
        withSonarQubeEnv('SonarQube') {
            sh 'mvn sonar:sonar -Dsonar.projectKey=MyDevSecOpsProject'
        }
    }
}
        }
    }
}
