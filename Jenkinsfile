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
        stage("Docker Push"){
            steps{
                script{
                    withDockerRegistry(credentialsId: azureContainerRegistryCredentials, url: azureContainerRegistry) {
                        dockerImage.push("${BUILD_NUMBER}")
                        dockerImage.push('latest')
                    }
                }
            }

        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'target/*.jar, target/*.war, target/*.zip'
        }
    }



}