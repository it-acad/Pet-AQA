pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'gradle clean build'
            }
        }

        stage('Test') {
            steps {
                sh 'gradle test'
            }
        }

        stage('Copy Allure Results') {
            steps {
                sh 'find . -type d -name "allure-results" -exec cp -r {} . \\;'
            }
        }

        stage('Allure Report') {
            steps {
                script {
                    allure([includeProperties: false, jdk: '', properties: [], reportBuildPolicy: 'ALWAYS', results: [[path: 'allure-results']]])
                }
            }
        }
    }
    }