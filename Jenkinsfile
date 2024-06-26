pipeline {

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }

    agent any

    tools {
        maven 'maven_3.8.8'
    }

    stages {
        stage('Code Compilation') {
            steps {
                echo 'code compilation is starting'
                sh 'mvn clean compile'
				echo 'code compilation is completed'
            }
        }/***
        stage('Sonarqube Code Quality') {
            environment {
                scannerHome = tool 'qube'
            }
            steps {
                withSonarQubeEnv('sonar-server') {
                    sh "${scannerHome}/bin/sonar-scanner"
                    sh 'mvn sonar:sonar'
                }
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }***\
         stage('Code Package') {
            steps {
                echo 'code packing is starting'
                sh 'mvn clean package'
				echo 'code packing is completed'
            }
        }
        stage('Building & Tag Docker Image') {
            steps {
                echo 'Starting Building Docker Image'
                sh 'docker build -t juli1995/travel .'
                sh 'docker build -t travel .'
                echo 'Completed  Building Docker Image'
            }
        }
        stage('Docker Image Scanning') {
            steps {
                echo 'Docker Image Scanning Started'
                sh 'java -version'
                echo 'Docker Image Scanning Started'
            }
        }
        stage(' Docker push to Docker Hub') {
           steps {
              script {
                 withCredentials([string(credentialsId: 'dockerhubCred', variable: 'dockerhubCred')]){
                 sh 'docker login docker.io -u juli1995 -p ${dockerhubCred}'
                 echo "Push Docker Image to DockerHub : In Progress"
                 sh 'docker push juli1995/travel:latest'
                 echo "Push Docker Image to DockerHub : In Progress"
                 sh 'whoami'
                 }
              }
           }
        }
        stage(' Docker Image Push to Amazon ECR') {
           steps {
              script {
                 withDockerRegistry([credentialsId:'ecr:ap-south-1:ecr-credentials', url:"https://773399105221.dkr.ecr.ap-south-1.amazonaws.com"]){
                 sh """
                 echo "List the docker images present in local"
                 docker images
                 echo "Tagging the Docker Image: In Progress"
                 docker tag travel:latest 773399105221.dkr.ecr.ap-south-1.amazonaws.com/travel:latest
                 echo "Tagging the Docker Image: Completed"
                 echo "Push Docker Image to ECR : In Progress"
                 docker push 773399105221.dkr.ecr.ap-south-1.amazonaws.com/travel:latest
                 echo "Push Docker Image to ECR : Completed"
                 """
                 }
              }
           }
        }/***
        stage('Upload the docker Image to Nexus') {
           steps {
              script {
                 withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
                 sh 'docker login http://13.233.152.101:8085/repository/travel/ -u admin -p ${PASSWORD}'
                 echo "Push Docker Image to Nexus : In Progress"
                 sh 'docker tag travel 13.233.152.101:8085/travel:latest'
                 sh 'docker push 13.233.152.101:8085/travel'
                 echo "Push Docker Image to Nexus : Completed"
                 }
              }
            }
        }***\
        stage ('Deploy App to K8s Cluster') {
            steps {
                sh 'whoami'
                sh 'kubectl apply -f 'nginx'
            }
        }
    }
}
