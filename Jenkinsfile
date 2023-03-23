pipeline {
    agent any
    
    environment {
        LANG = 'en_US.UTF-8'
    }

    stages { 
        stage('Git Pull'){
            steps{
                git url: 'https://github.com/Yuvraj-Sharma-2000/Calculator.git', branch: 'master',
                credentialsId:'0b70dc81-32f2-4e04-bf3b-02073a60996b'
            }
        }
        stage('Maven Build and Run Tests'){
            steps{
                sh "mvn clean install"
            }
        }
        stage('Image Build'){
            steps{
                sh "docker build -t yuvrajsharma2000/docker_image_calculator:latest ."
            }
        }

        stage('Image Deploy') {
          steps {
            withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'docker_username', passwordVariable: 'docker_password')]) {
              sh "echo $docker_password | docker login --username $docker_username --password-stdin"
              sh "docker push yuvrajsharma2000/docker_image_calculator:latest"
            }
          }
        }
        stage('Delete previous Image') {
            steps {
                sh '''
                    # Remove all images with the tag <none>
                    docker rmi --force $(docker images | grep "<none>" | awk '{print $3}')
                '''
            }
        }
        stage('Ansible Deploy') {
            steps {                              
                withCredentials([
                        [
                            $class: 'UsernamePasswordMultiBinding',
                            credentialsId: 'dockerhub',
                            usernameVariable: 'DOCKERHUB_USERNAME',
                            passwordVariable: 'DOCKERHUB_PASSWORD'
                        ],
                        [
                            $class: 'UsernamePasswordMultiBinding',
                            credentialsId: 'ansibleUser',
                            usernameVariable: 'ANSIBLE_USERNAME',
                            passwordVariable: 'ANSIBLE_PASSWORD'
                        ]
                    ]) {
                        ansiblePlaybook(
                            installation: 'Ansible',
                            inventory: 'inventory',
                            playbook: 'p3.yml',
                            colorized: true,
                            disableHostKeyChecking: true,
                            extraVars: [
                                'jenkins_credentials_username': "${DOCKERHUB_USERNAME}",
                                'jenkins_credentials_password': "${DOCKERHUB_PASSWORD}",
                                'ansible_credentials_username': "${ANSIBLE_USERNAME}",
                                'ansible_credentials_password': "${ANSIBLE_PASSWORD}"
                            ]
                        )
                    }

                
            }
        }
    }
    options {
        skipDefaultCheckout()
        timestamps()
    }
}
