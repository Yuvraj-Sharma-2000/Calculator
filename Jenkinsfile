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
        stage('Delete previous Image and Containers') {
            steps {
                sh '''
                    # Get the IDs of all images with the tag <none>
                    NONE_IMAGES=$(docker images | grep "<none>" | awk '{print $3}')

                    # Delete all of the <none> images
                    for IMAGE in $NONE_IMAGES
                    do
                      docker rmi --force $IMAGE
                    done

                    # Delete all of the containers associated with the <none> images
                    for IMAGE in $NONE_IMAGES
                    do
                      # Get the container IDs for the image
                      CONTAINER_IDS=$(docker ps -a | grep $IMAGE | awk '{print $1}')

                  # Remove the containers
                  for CONTAINER_ID in $CONTAINER_IDS
                  do
                    docker rm --force $CONTAINER_ID
                  done
                done
                '''
            }
        }
        stage('Ansible Deploy') {
            steps {
//                 withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
//                     ansiblePlaybook(
//                         installation: 'Ansible',
//                         inventory: 'inventory',
//                         playbook: 'p3.yml',
//                         colorized: true,
//                         disableHostKeyChecking: true,
//                         extraVars: [
//                             'jenkins_credentials_username': "${DOCKERHUB_USERNAME}",
//                             'jenkins_credentials_password': "${DOCKERHUB_PASSWORD}"
//                         ]
//                     )
//                 }
                
                
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
