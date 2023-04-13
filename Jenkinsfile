pipeline {

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }

    agent any

    tools {
        maven 'maven_3.8.8'
    }

    stages {
        stage('Checking JAVA, Maven,git') {
            steps {
                echo 'Code Compilation is In Progress!'
                sh 'mvn --version'
                sh 'java --version'
                sh 'git --version'
                sh 'whoami'
                echo "this pipeline is running via Jenkins User"
            }
        }
        stage('Code Compilation') {
            steps {
                echo 'Code Compilation is In Progress!'
                sh 'mvn clean compile'
                sh 'mvn clean compile'
            }
        }
        stage('Code package') {
            steps {
                echo 'Code packaging is in Progress!'
                sh 'mvn clean package'
                sh 'mvn clean package'
            }
        }
        stage('Create docker image') {
            steps {
                echo 'Hello JDX'
                sh 'java -version'
			}
		}
        stage('Docker Image to Docker HUB') {
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
            }
        }
        stage('Push Doker Image to AWS ECR') {
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
			}
		}
        stage('Push Doker Image to AWS ECR') {
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
			}
		}
        stage('Deploy APP to K8s Cluser') {
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
			}
		}
    }
}


