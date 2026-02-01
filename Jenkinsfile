pipeline {
    agent any
    
    tools {
        // This must match the name in Manage Jenkins > Tools
        maven 'maven' 
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/saqib258/my-jenkins-project.git'
            }
        }

        // --- PASTE THE VERIFY STAGE HERE ---
        stage('Verify Environment') {
            steps {
                sh 'ls -la'       // Confirms pom.xml was pulled from GitHub
                sh 'java -version' // Confirms JDK 17 is active
                sh 'mvn -version'  // Confirms Maven is installed correctly
                sh 'docker --version'
            }
        }

        stage('Build & Package') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Static Security Analysis (SonarQube)') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=DevSecOps-Thesis'
                }
            }
        }
    }
}

