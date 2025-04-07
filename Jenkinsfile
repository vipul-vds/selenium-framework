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
		            def output = bat(
		                script: '@echo off & for /f "delims=" %%i in (\'dir /b /ad /o-d reports\') do (echo %%i & goto :done)\n:done',
		                returnStdout: true
		            ).trim()
		            def latestDir = "reports\\${output}"
		            echo "Latest report directory: ${latestDir}"
		            env.LATEST_REPORT_DIR = latestDir
		        }
		    }
		}

        stage('Archive Report') {
		    steps {
		        script {
		            def reportFile = "${env.LATEST_REPORT_DIR}\\testReport.html"
		            echo "Archiving: ${reportFile}"
		            if (fileExists(reportFile)) {
		                archiveArtifacts artifacts: reportFile, fingerprint: true
		            } else {
		                error "File not found: ${reportFile}"
		            }
		        }
		    }
		}

        stage('Zip Report') {
            steps {
                powershell """
                    \$source = '${env.LATEST_REPORT_DIR}'
                    \$destination = 'ArchivedReport.zip'
                    Compress-Archive -Path \$source -DestinationPath \$destination -Force
                """
            }
        }

        Optional: 
        stage('Copy Log File') {
            steps {
                script {
                    // Only use if you have a log file being created elsewhere
                    bat 'if exist logs\\application.log copy logs\\application.log .'
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline finished."
			
            emailext(
            subject: "Test Mail from Jenkins",
            body: "Hi,\n\nThis is a test email from Jenkins.\n\nThanks.",
            to: 'vipulpmalde@gmail.com',
            replyTo: 'vipulpmalde@gmail.com',
            recipientProviders: [], 
            attachLog: false
        )
        }
    }
}