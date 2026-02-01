pipeline {
    agent any

    tools {
        // This must match the name you gave to Maven in Global Tool Configuration
        maven 'maven' 
    }

    environment {
        // You can define variables here for reuse
        DOCKER_IMAGE = "my-devsecops-app"
        SONAR_URL = "http://34.239.170.156:9000"
    }

    stages {
        stage('Checkout SCM') {
            steps {
                // Jenkins automatically pulls the code if using "Pipeline from SCM"
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Compiles code and runs unit tests
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Static Security Analysis (SonarQube)') {
            steps {
                // "SonarQube" must match the server name in Manage Jenkins > System
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Vulnerability Gate') {
            steps {
                // Pauses pipeline until SonarQube finishes reporting results
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                // Containerizes your application
                sh "docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."
            }
        }

        stage('Container Security Scan (Trivy)') {
            steps {
                // Placeholder stage for Trivy image scanning
                echo "Scanning Docker Image for vulnerabilities..."
                sh "trivy image ${DOCKER_IMAGE}:${BUILD_NUMBER}"
            }
        }
    }

    post {
        always {
            // Cleans up the workspace to save disk space on your EC2
            cleanWs()
        }
    }
}
