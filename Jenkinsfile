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
        stage('Remove Docker image with no tag') {
            steps {
                sh 'docker rmi --force $(docker images -f "dangling=true" -q)'
            }
        }
        stage('Start Container') {
          steps {
            sh "docker run -d --name calculator yuvrajsharma2000/docker_image_calculator"
          }
        }

        stage('Monitor') {
            steps {
                // Collect logs and metrics
                sh "docker logs docker_image_calculator > my-calculator.log"
                sh "docker stats my-calculator --no-stream --format '{{.Name}},{{.CPUPerc}},{{.MemUsage}},{{.NetIO}},{{.BlockIO}},{{.PIDs}}' > my-calculator.stats"

                // Send logs and metrics to Elasticsearch
                logstash {
                    configFile 'logstash.conf'
                }
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
