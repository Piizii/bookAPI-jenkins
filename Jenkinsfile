pipeline {
    agent any
    stages {
        stage('Compile Stage') {
            steps {
                withMaven(maven: 'maven_3_10') {
                    bat 'mvn clean compile'
                }
            }
        }
        stage('Testing Stage') {
            steps {
                withMaven(maven: 'maven_3_10') {
                    bat 'mvn test'
                }
            }
        }
        stage('Deployment Stage') {
            steps {
                withMaven(maven: 'maven_3_10') {
                    bat 'mvn tomcat7:undeploy'
                }
                withMaven(maven: 'maven_3_10') {
                    bat 'mvn tomcat7:deploy'
                }
            }
        }
    }
}