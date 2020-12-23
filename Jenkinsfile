#!groovy

pipeline {
    agent {
        label 'linux'
    }
    parameters {
        string(name: 'RELEASE_VERSION', defaultValue: '9.0.0', description: '')
        string(name: 'POST_RELEASE_SNAPSHOT_VERSION', defaultValue: '9.0.1-SNAPSHOT', description: '')
        booleanParam(name: 'RELEASE', defaultValue: false, description: '')
    }
    options {
        timestamps()
        skipDefaultCheckout()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }
    stages {
        stage('Cleanup') {
            steps {
                cleanWs()
            }
        }
        stage('Clone') {
            steps {
                sshagent(['github-creds']) {
                    git credentialsId: 'github-creds', url: 'git@github.com:automatictester/wiremock-maven-plugin.git'
                }
            }
        }
        stage('Purge') {
            steps {
                sh 'rm -rf ~/.m2/repository/uk/co/automatictester/wiremock-maven-plugin/'
            }
        }
        stage('Set release version number') {
            steps {
                sh "./mvnw versions:set -DnewVersion=${params.RELEASE_VERSION}"
                sh "git add -A; git commit -m 'Release version bump'"
            }
        }
        stage('Integration test') {
            steps {
                sh './mvnw clean verify'
            }
        }
        stage('Tag release') {
            steps {
                sh "git tag ${params.RELEASE_VERSION}"
            }
        }
        stage('Release artefacts') {
            when {
                expression {
                    "${params.RELEASE}".toBoolean() && "${env.BRANCH_NAME}" == "master"
                }
            }
            steps {
                withCredentials([string(credentialsId: 'gpg-passphrase', variable: 'GPGPP')]) {
                    sh "./mvnw clean deploy -P release -Dgpg.passphrase=${GPGPP} -DskipTests -Dinvoker.skip=true"
                }
            }
        }
        stage('Set snapshot version number') {
            steps {
                sh "./mvnw versions:set -DnewVersion=${POST_RELEASE_SNAPSHOT_VERSION}"
                sh "git add -A; git commit -m 'Post-release version bump'"
            }
        }
        stage('Push release to origin') {
            when {
                expression {
                    "${params.RELEASE}".toBoolean() && "${env.BRANCH_NAME}" == "master"
                }
            }
            steps {
                sshagent(['github-creds']) {
                    sh "git push --set-upstream origin master; git push --tags"
                }
            }
        }
    }
}