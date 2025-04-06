pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1' // Match the tool name in Jenkins
        jdk 'JDK11'         // Match the tool name in Jenkins
    }
    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/your-username/your-repo-name.git'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Find Latest Extent Report Folder') {
            steps {
                script {
                    def reportBaseDir = "reports"
                    def folders = sh(
                        script: "ls -td ${reportBaseDir}/*/ | head -1",
                        returnStdout: true
                    ).trim()
                    env.REPORT_PATH = folders
                    echo "Latest Report Folder: ${env.REPORT_PATH}"
                }
            }
        }
        stage('Publish Extent Report') {
            steps {
                publishHTML([
                    reportDir: "${env.REPORT_PATH}",
                    reportFiles: 'index.html',
                    reportName: 'Extent Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                ])
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            emailext {
				to: ‘vds.vipul@gmail.com’,
				subject: “Build: ${currentBuild.fullDisplayName} - ${currentBuildResult}”,
				body: “Build ${env.BUILD_URL} completed with status: ${currentBuild.result}”, 
				attachmentsPattern: “${env.REPORT_PATH}/indexhtml”
			}
        }
    }
}


