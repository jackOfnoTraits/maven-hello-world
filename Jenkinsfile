
pipeline {
    agent any

    options {
        timeout(time: 10, unit: 'MINUTES')
        buildDiscarder(logRotator(
            numToKeepStr: '10',
            artifactNumToKeepStr: '5'
        ))
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'echo "Building branch: $BRANCH_NAME"'
                sh 'git log -1 --oneline'
            }
        }

        stage('Validate') {
            steps {
                sh 'mvn validate'
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts(
                    artifacts: 'target/*.jar',
                    allowEmptyArchive: false
                )
            }
        }
    }

    post {
        success {
            echo "Build succeeded for branch: ${env.BRANCH_NAME}"
        }
        failure {
            echo "Build failed for branch: ${env.BRANCH_NAME}"
        }
        always {
            echo "Result: ${currentBuild.currentResult}"
        }
    }
}

