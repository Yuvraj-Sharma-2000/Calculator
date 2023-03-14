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
            withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'docker_username', passwordVariable: 'docker_password')]) {
              sh "echo $docker_password | docker login --username $docker_username --password-stdin"
              sh "docker push yuvrajsharma2000/docker_image_calculator:latest"
            }
          }
        }

//         stage('Remove previous build') {
//             steps {
//                 sh 'docker rmi --force $(docker images -f "dangling=true" -q)'
//             }
//         }
        stage('Delete Image and Containers') {
            steps {
                sh '''
                    # get the IDs of all containers with the tag <none>
                    # NONE_CONTAINERS=$(docker ps -a | grep "<none>" | awk '{print $1}')

                    # delete all of the <none> containers
                    # for CONTAINER in $NONE_CONTAINERS
                    # do
                    #  docker rm $CONTAINER
                    # done

                    # get the IDs of all images with the tag <none>
                    NONE_IMAGES=$(docker images | grep "<none>" | awk '{print $3}')

                    # delete all of the <none> images
                    for IMAGE in $NONE_IMAGES
                    do
                      docker rmi --force $IMAGE
                    done
                    
                    for IMAGE in $NONE_IMAGES
                    do
                      docker rm --force $(docker ps -a | grep "$IMAGE" | awk '{print $1}') 
                    done
                '''
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
