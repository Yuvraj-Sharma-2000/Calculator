# Calculator
SPE mini project

Roadmap for Scientific Calculator Program:

1) Develop the scientific calculator program with menu-driven operations for Square root, Factorial, Natural logarithm, and Power function.
2) Initialize a source control management tool such as Git and create a repository for the scientific calculator program.
3) Write unit tests for the calculator program using testing frameworks such as JUnit.
4) Integrate the testing framework with the source control management tool using tools like Jenkins.
5) Use a build tool Maven to build your code and package it into an executable JAR file.
6) Containerize your code using Docker and push the created Docker image to Docker Hub.
7) Use a configuration management tool Ansible, to do configuration management and deploy the Docker image to a managed host.
8) Deploy the scientific calculator program on your local machine or any other 3rd party cloud.
9) Monitor the scientific calculator program using the ELK stack. Generate log files for the mini project and pass them to the ELK stack for monitoring.

## To run the program
copy ssh into the host defined in inventory file like
```
ssh-copy-id ansible_usr@192.168.0.3
```
This will ask for password of that user, on validation you can login to their user as 
```
ssh ansible_usr@192.168.0.3
```

You will have an image named yuvrajsharma2000/docker_image_calculator:latest in your docker images
Start and run the image
```
docker images
docker run -it <image_name>
```
