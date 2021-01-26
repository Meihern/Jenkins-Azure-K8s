pipeline{
    agent any
    tools {
        maven 'Maven-3.6.3'
    }

    stages {
        stage("Maven Build"){
            steps {
                sh 'mvn package'
            }
        }
        stage("Docker Build"){
            steps {
                sh 'echo "Building Docker Image ..."'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar, target/*.war, target/*.zip'
        }
    }



}