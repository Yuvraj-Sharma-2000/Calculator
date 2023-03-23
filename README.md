# Calculator
SPE mini project

Roadmap for  Scientific Calculator Program:

1) Develop the  scientific calculator program with menu-driven operations for Square root, Factorial, Natural logarithm, and Power function.
2) Initialize a source control management tool such as Git and create a repository for the scientific calculator program.
3) Write unit tests for the calculator program using testing frameworks such as JUnit.
4) Integrate the testing framework with the source control management tool using tools like Jenkins.
5) Use a build tool Maven to build your code and package it into an executable JAR file.
6) Containerize your code using Docker and push the created Docker image to Docker Hub.
7) Use a configuration management tool Ansible, to do configuration management and deploy the Docker image to a managed host.
8) Deploy the scientific calculator program on your local machine or any other 3rd party cloud.
9) Monitor the scientific calculator program using the ELK stack. Generate log files for the mini project and pass them to the ELK stack for monitoring.

## Ansible
Ansible is a tool that automates repetitive tasks that system administrators and developers perform to configure and manage their systems. It uses a simple language to describe what needs to be done, and then executes those tasks on one or more systems remotely, without needing to install any additional software on the target systems.

Ansible simplifies automation by providing a large number of pre-built modules that can be used to automate common tasks, such as managing packages, configuring servers, and deploying applications. It is also designed to integrate with other tools and systems, making it a powerful and versatile automation tool.

Overall, Ansible simplifies the process of managing and configuring systems, saving time and improving efficiency, making it an ideal choice for organizations looking to streamline their IT operations.


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
docker run -it yuvrajsharma2000/docker_image_calculator
```

## Explanations 
### Dockerfile
This is a Dockerfile that defines how to build a Docker image for running a Java application. Here's a breakdown of what each line does:

FROM openjdk:11 - This line sets the base image for the Docker image to be built. In this case, it's an official OpenJDK 11 image from Docker Hub.

COPY ./target/calculator-1.0-SNAPSHOT.jar ./ - This line copies the calculator-1.0-SNAPSHOT.jar file from the ./target directory (relative to the Dockerfile) into the root directory of the Docker image.

WORKDIR ./ - This line sets the working directory for the Docker image to the root directory.

CMD ["java", "-jar", "calculator-1.0-SNAPSHOT.jar"] - This line specifies the command that should be run when the Docker image is run. In this case, it runs the java command with the -jar option, passing in the calculator-1.0-SNAPSHOT.jar file as the argument.

Overall, this Dockerfile is needed because it provides a way to package and distribute the Java application in a self-contained, isolated environment that can be easily deployed and run on any system that has Docker installed.

### inventory
In Ansible, an inventory file is a text file that contains a list of the hosts or nodes that Ansible will manage. The inventory file specifies the IP addresses or domain names of the hosts, along with other optional information such as the SSH port number, username, and password.

In your example, the inventory file defines a group named "containers" with a single host specified by the IP address "127.0.0.1". The host has three Ansible variables defined:

ansible_user: Specifies the username that Ansible will use to connect to the host over SSH.
ansible_ssh_pass: Specifies the password that Ansible will use to authenticate with the host over SSH.
ansible_sudo_pass: Specifies the password that Ansible will use when executing commands with sudo or running tasks with elevated privileges.

### playbook.yml
In Ansible, a playbook is a file that describes a set of tasks to be executed on one or more hosts. It defines the hosts on which the tasks will be executed, and the sequence of tasks that will be performed.

In your example, the playbook is named playbook.yml, and it defines a set of tasks to run a Docker container that runs a Java application. Here's a breakdown of what each section does:

name: Run Docker container - This is a descriptive name for the playbook, which will be displayed when the playbook is executed.

hosts: containers - This specifies the group of hosts on which the playbook will be executed. In this case, the playbook will be executed on the hosts in the "containers" group.

become: true - This indicates that Ansible should elevate privileges (i.e., run tasks as a privileged user) when executing the tasks in this playbook.

tasks: - This section lists the tasks that will be performed.

- name: Pull Docker image - This task pulls a Docker image named "yuvrajsharma2000/docker_image_calculator" from a Docker registry.

docker_image: - This is an Ansible module that manages Docker images.

name: Run Docker container - This task starts a Docker container named "my-calculator" from the "yuvrajsharma2000/docker_image_calculator" image.

docker_container: - This is an Ansible module that manages Docker containers.

image: yuvrajsharma2000/docker_image_calculator - This specifies the Docker image that the container should be based on.

state: started - This indicates that the container should be started (if it isn't already running).

command: java -jar /calculator-1.0-SNAPSHOT.jar - This specifies the command to run inside the container, which in this case is a Java application located at /calculator-1.0-SNAPSHOT.jar.

Overall, this playbook defines a set of tasks that can be used to deploy and run a Docker container that runs a Java application. When executed on the hosts in the "containers" group, Ansible will pull the Docker image, start the container, and run the specified command inside the container.

### META-INF/MANIFEST.MF
META-INF/MANIFEST.MF is a file that contains metadata about a JAR file, including information about the contents of the JAR file and how it should be executed. It is a standard part of the JAR file format and is used by Java applications to define the entry point for the application and other important metadata.

One of the key attributes that can be specified in the MANIFEST.MF file is the Main-Class attribute. This attribute specifies the fully-qualified name of the Java class that contains the main() method for the application. The main() method is the entry point for the Java application, and it is the method that is executed when the application is started.
