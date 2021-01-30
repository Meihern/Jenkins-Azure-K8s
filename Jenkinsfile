pipeline{
    environment{
        azureContainerRegistry = "bookstoreapp.azurecr.io"
        imageName = "bookstoreapp-spring"
        azureContainerRegistryCredentials = 'bookstoreapp-acr'
    }
    agent any
    tools {
        maven 'Maven-3.6.3'
    }
    stages {
        stage("Maven Build"){
            steps {
                sh 'mvn clean package'
            }
        }
        stage("Docker Build"){
            steps {
                script{
                    dockerImage = docker.build azureContainerRegistry+"/"+imageName
                }
            }
        }
        stage("ACR Docker Push"){
            steps{
                script{
                    withDockerRegistry(credentialsId: azureContainerRegistryCredentials, url: "https://"+azureContainerRegistry+"/v2") {
                        dockerImage.push("${BUILD_NUMBER}")
                        dockerImage.push('latest')
                    }
                }
            }

        }
        stage ('AKS Deploy') {
            steps {
                sh 'kubectl apply -f ./bookstore-deployment-config.yaml'
                sh "kubectl set image deployment/bookstore-deployment bookstore=${azureContainerRegistry}/${imageName}:${BUILD_NUMBER}"
            }
        }
    }
    post {
        success {
            archiveArtifacts artifacts: 'target/*.jar, target/*.war, target/*.zip'
        }
    }
}