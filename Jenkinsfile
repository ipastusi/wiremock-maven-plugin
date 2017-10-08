#!groovy

pipeline {
    agent any
    tools {
        maven 'M3'
        jdk 'jdk8'
    }
    options {
        timestamps()
        skipDefaultCheckout()
    }
    stages {
        stage('Cleanup') {
            steps {
                step([$class: 'WsCleanup'])
            }
        }
        stage('Clone') {
            steps {
                sshagent(["${GIT_CREDENTIALS_ID}"]) {
                    sh "git clone ${REPO_URL} ."
                }
            }
        }
        stage('Purge') {
            steps {
                sh 'rm -rf ~/.m2/repository/uk/co/deliverymind/'
            }
        }
        stage('Set release version number') {
            when {
                expression {
                    "${TEST_ONLY}" == "false"
                }
            }
            steps {
                sh "mvn versions:set -DnewVersion=${RELEASE_VERSION}"
                sh "git add -A; git commit -m 'Release version bump'"
            }
        }
        stage('Integration test') {
            steps {
                sh "mvn clean verify"
            }
            post {
                always {
                    jacoco execPattern: '**/jacoco.exec'
                }
            }
        }
        stage('Tag release') {
            when {
                expression {
                    "${TEST_ONLY}" == "false"
                }
            }
            steps {
                sh "git tag ${RELEASE_VERSION}"
            }
        }
        stage('Release artefacts') {
            when {
                expression {
                    "${TEST_ONLY}" == "false" && "${DRY_RUN}" == "false"
                }
            }
            steps {
                sh "mvn clean deploy -P release -Dgpg.passphrase=${GPG_PASSPHRASE} -Dskip.integration.tests=true"
            }
        }
        stage('Set snapshot version number') {
            when {
                expression {
                    "${TEST_ONLY}" == "false"
                }
            }
            steps {
                sh "mvn versions:set -DnewVersion=${POST_RELEASE_SNAPSHOT_VERSION}"
                sh "git add -A; git commit -m 'Post-release version bump'"
            }
        }
        stage('Push release to origin') {
            when {
                expression {
                    "${TEST_ONLY}" == "false" && "${DRY_RUN}" == "false"
                }
            }
            steps {
                sshagent(["${GIT_CREDENTIALS_ID}"]) {
                    sh "git push --set-upstream origin master; git push --tags"
                }
            }
        }
    }
}