pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Clean workspace') {
            steps {
                script {
                    deleteDir()
                }
            }
        }
        stage('Build') {
            steps {
                git 'https://github.com/ReverFox/EdProject.git'
                bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                always {
                   allure includeProperties:
                     false,
                     jdk: '',
                     results: [[path: 'target/allure-results']],
                     report: 'target/allure-report'
                }
            }
        }
    }
}
