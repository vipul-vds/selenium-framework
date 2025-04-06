pipeline {
    agent any

    tools {
        maven 'Maven 3.x'
        jdk 'JDK 17'
    }

    environment {
        REPORT_BASE_DIR = "reports"
    }

    stages {
        stage('Checkout') {
            steps {
            checkout([$class: 'GitSCM', branches: [[name: 'origin/main']], userRemoteConfigs: [[url: 'https://github.com/vipul-vds/selenium-framework.git']]])
            }
        }

        stage('Build and Test') {
            steps {
                bat 'mvn clean test -Dmaven.test.failure.ignore=true'
            }
        }

        stage('Find Latest Report') {
            steps {
                script {
                    def latestDir = bat(
    					script: 'for /f "delims=" %%i in (\'dir /b /ad /o-d reports\') do @echo reports\\%%i & goto :done\n:done',
    					returnStdout: true
						).trim()
					env.LATEST_REPORT_DIR = latestDir
                }
            }
        }

        stage('Archive Report') {
            steps {
                script {
                    def reportPath = "${env.LATEST_REPORT_DIR}\\testResult.xml"
                    archiveArtifacts artifacts: reportPath, allowEmptyArchive: true
                }
            }
        }

        stage('Zip Report') {
            steps {
                powershell """
           			Compress-Archive -Path \\"${env.LATEST_REPORT_DIR}\\" -DestinationPath \\"ArchivedReport.zip\\" -Force
        		"""
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
                to: 'vipulpmalde@gmail.com'
            )
        }
    }
}