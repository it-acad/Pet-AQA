pipeline {
    environment {
        HEADLESS_MODE = 'true'
        PATH = '$PATH:/usr/local/bin'
    }
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Build Docker image') {
            steps {
                sh 'docker build -t docker .'
            }
        }

        stage('Run Docker container') {
            steps {
                script {
                    docker.image('docker').run("-p 8080:8080 -p 50000:50000 --name my-ui-tests -d")
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    docker.image('docker').inside {
                        sh "./gradlew clean test --info --console=plain -Dtest.single=*Test* -Dorg.gradle.test.worker.maxDaemonIdleTimeMs=2000 -Dorg.gradle.test.worker.maxHeapSize=1024m -Dorg.gradle.caching=false -DignoreTestFailures=true"
                    }
                }
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

        stage('Stop Docker container') {
            steps {
                sh 'docker stop my-ui-tests'
                sh 'docker rm my-ui-tests'
            }
        }
    }
}