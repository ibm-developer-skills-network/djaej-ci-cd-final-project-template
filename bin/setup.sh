#!/bin/bash
echo "**************************************************"
echo " Setting up CI/CD Final Project Java Environment"
echo "**************************************************"

echo "*** Installing JDK 21..."
sudo apt-get update
sudo DEBIAN_FRONTEND=noninteractive apt-get install -y openjdk-21-jdk

echo "*** Checking the Java version..."
java --version

echo "*** Installing Maven..."
sudo DEBIAN_FRONTEND=noninteractive apt-get install -y maven

echo "*** Checking the Maven version..."
mvn --version

echo "*** Installing Docker..."
sudo apt-get update
sudo DEBIAN_FRONTEND=noninteractive apt-get install -y apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get update
sudo DEBIAN_FRONTEND=noninteractive apt-get install -y docker-ce docker-ce-cli containerd.io

echo "*** Configuring the developer environment..."
echo "# CI/CD Final Project additions" >> ~/.bashrc
echo "export GITHUB_ACCOUNT=$GITHUB_ACCOUNT" >> ~/.bashrc
echo 'export PS1="\[\e]0;\u:\W\a\]${debian_chroot:+($debian_chroot)}\[\033[01;32m\]\u\[\033[00m\]:\[\033[01;34m\]\W\[\033[00m\]\$ "' >> ~/.bashrc
echo "export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64" >> ~/.bashrc
echo "export PATH=\$PATH:\$JAVA_HOME/bin" >> ~/.bashrc

echo "*** Creating Maven wrapper..."
chmod +x ./mvnw

echo "*** Building the application..."
./mvnw clean package -DskipTests

echo "**************************************************"
echo " CI/CD Final Project Java Environment Setup Complete"
echo "**************************************************"
echo ""
echo "Use 'exit' to close this terminal and open a new one to initialize the environment"
echo ""