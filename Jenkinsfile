pipeline {
    agent any
    
    tools {
        // Ensure this name 'maven' matches your Jenkins Global Tool Configuration
        maven 'maven' 
    }

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
        }

        stage('Static Security Analysis (SonarQube)') {
            steps {
                // 'SonarQube' must match the server name in Manage Jenkins > System
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=DevSecOps-Thesis'
                }
            }
        }

        stage("Quality Gate Check") {
            steps {
                // This waits for SonarQube to finish and tell Jenkins the result.
                // It will fail the pipeline if the code does not meet security standards.
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}

