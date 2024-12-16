pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

       

        stage('SonarQube Analysis') {
            environment {
                SONAR_HOST_URL = 'http://192.168.27.55:9000'
                SONAR_AUTH_TOKEN = credentials('sonarqube-user-token') // Replace with your Jenkins credentials ID
            }
            steps {
                sh '''
                    /var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/Default_Maven/bin/mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=siddhopant123_VoIP-simple-application_5d31848d-4277-4652-8dc7-52af7283d41d \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.tests=src/test/java \
                        -Dsonar.projectBaseDir=$WORKSPACE \
                        -Dsonar.host.url=$SONAR_HOST_URL \
                        -Dsonar.token=$SONAR_AUTH_TOKEN \
                        -Dsonar.language=java \
                        -Dsonar.java.binaries=target/classes
                '''
            }
        }
    }
}
