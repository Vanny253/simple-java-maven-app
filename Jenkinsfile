pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'my-java-app'
        DOCKER_CONTAINER = 'java-staging'
        DOCKER_PORT = '8081:8081'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Vanny253/simple-java-maven-app.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    echo 'Building the project...'
                    bat 'mvn -v'  // Verify Maven installation
                    bat 'mvn compile'  // Compile the project
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    echo 'Running tests...'
                    bat 'mvn test'  // Run tests
                }
            }
        }

        stage('Generate Code Coverage') {
            steps {
                script {
                    echo 'Generating code coverage report...'
                    bat 'mvn jacoco:prepare-agent test jacoco:report'  // Generate code coverage report
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    echo 'Packaging the project into a JAR file...'
                    bat 'mvn package'  // Package the app
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker image...'
                    writeFile file: 'Dockerfile', text: '''
                        FROM openjdk:17-jdk
                        COPY target/my-app-1.0-SNAPSHOT.jar /usr/app/
                        WORKDIR /usr/app
                        CMD ["java", "-jar", "my-app-1.0-SNAPSHOT.jar"]
                    '''
                    bat 'docker build -t my-java-app .'  // Build Docker image
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    echo 'Running the Docker container...'
                    bat 'docker rm -f java-staging'
                    bat 'docker run -d --name java-staging -p 8081:8081 my-java-app'  // Start the Docker container
                }
            }
        }

        stage('Deployment Verification') {
    steps {
        script {
            echo 'Waiting for the application to start...'

            // Wait up to 30 seconds for the service to respond
            def timeout = 30
            def elapsed = 0
            def success = false

            while (elapsed < timeout) {
                def result = bat(script: 'curl --silent --fail http://localhost:8081/', returnStatus: true)
                if (result == 0) {
                    success = true
                    break
                }
                sleep(time: 2, unit: 'SECONDS')
                elapsed += 2
            }

            if (!success) {
                error("Application did not start within ${timeout} seconds")
            }

            echo '✅ Application is running successfully!'
        }
    }
}

    }

    post {
        always {
            echo 'Cleaning up workspace...'
            deleteDir()  // Clean up the workspace
        }
        success {
            echo '✅ Build and Deployment succeeded!'
        }
        failure {
            echo '❌ Build or Deployment failed!'
        }
    }
}
