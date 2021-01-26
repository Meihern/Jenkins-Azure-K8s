pipeline{
    def app
    def azureContainerRegistry = "bookstoreapp.azurecr.io"
    def imageName = "bookstoreapp-spring"
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
                app = docker.build(azureContainerRegistry+"/"+imageName)
            }
        }
        stage("Docker Push"){
            withDockerRegistry(credentialsId: 'bookstoreapp-acr', url: 'bookstoreapp.azurecr.io') {
                app.push("${env.BUILD_NUMBER}")
                app.push("latest")
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'target/*.jar, target/*.war, target/*.zip'
        }
    }



}