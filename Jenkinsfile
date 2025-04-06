pipeline {
    agent any

    tools {
        maven 'Maven 3'
        jdk 'JDK 11'
    }

    environment {
        REPORT_BASE_DIR = "reports"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/your-repo.git'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Find Latest Report') {
            steps {
                script {
                    def latestDir = sh(
                        script: "ls -dt ${REPORT_BASE_DIR}/*/ | head -1",
                        returnStdout: true
                    ).trim()
                    env.LATEST_REPORT_DIR = latestDir
                }
            }
        }

        stage('Archive Report') {
            steps {
                script {
                    def reportPath = "${env.LATEST_REPORT_DIR}testResult.xml"
                    archiveArtifacts artifacts: reportPath, allowEmptyArchive: true
                }
            }
        }

        stage('Zip Report') {
            steps {
                sh "zip -r ${env.LATEST_REPORT_DIR}ExtentReport.zip ${env.LATEST_REPORT_DIR}"
            }
        }
    }

    post {
        always {
            emailext (
                subject: "Job '${env.JOB_NAME}' - Build #${env.BUILD_NUMBER} - ${currentBuild.currentResult}",
                body: """<p>Build Status: ${currentBuild.currentResult}</p>
                         <p>Project: ${env.JOB_NAME}</p>
                         <p>Build Number: ${env.BUILD_NUMBER}</p>
                         <p>Report: <a href="${env.BUILD_URL}artifact/${env.LATEST_REPORT_DIR}index.html">Click here</a></p>""",
                mimeType: 'text/html',
                attachmentsPattern: "${env.LATEST_REPORT_DIR}ExtentReport.zip",
                to: 'vipulpmalde@gmail.com',
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }
    }
}
