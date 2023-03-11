pipeline {
    agent any
    
    environment {
        LANG = 'en_US.UTF-8'
        registry = "yuvrajsharma2000/calculator"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }
    
    stages {
        stage('Git Pull'){
            steps{
                git url: 'https://github.com/Yuvraj-Sharma-2000/Calculator.git', branch: 'master',
                credentialsId:'0b70dc81-32f2-4e04-bf3b-02073a60996b'
            }
        }
        stage('Maven Build'){
            steps{
                sh "mvn clean install"
            }
        }
        stage('Test') {
            steps {
                    sh "mvn test"
                }
            }
        stage('Image Build'){
            steps{
                sh "docker build -t yuvrajsharma2000/docker_image_calculator:latest ."
            }
        }

        stage('Image Deploy') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'yuvrajsharma2000', passwordVariable: 'Centra@5848')]) {
                    sh "docker login -u yuvrajsharma2000 -p Centra@5848"
                    sh "docker push yuvrajsharma2000/docker_image_calculator:latest"
                }
            }
        }
        stage('Remove Local Image'){
            steps{
                sh "docker rmi -f yuvrajsharma2000/docker_image_calculator:latest"
            }
        }
        stage('Ansible Deploy') {
            steps {
                ansiblePlaybook(
                    installation: 'Ansible',
                    inventory: 'inventory',
                    playbook: 'p3.yml',
                    colorized: true,
                    disableHostKeyChecking: true
                )
            }
        }
    }

    options {
        skipDefaultCheckout()
        timestamps()
    }
}
