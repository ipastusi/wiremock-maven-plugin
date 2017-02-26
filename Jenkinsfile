#!groovy

pipeline {
    agent any
    stages {
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
                withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                    sh "(cd plugin; mvn versions:set -DnewVersion=${RELEASE_VERSION})"
                    sh "git add -A; git commit -m 'Release version bump'"
                }
            }
        }
        stage('Build') {
            steps {
                withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                    sh "(cd plugin; mvn clean install)"
                }
            }
        }
        stage('Integration test') {
            steps {
                withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                    sh "(cd plugin-it; mvn clean verify)"
                    sh "mvn -pl plugin-it clean verify"
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
                withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                    sh "(cd plugin; mvn clean deploy -P release -Dgpg.passphrase=${GPG_PASSPHRASE})"
                }
            }
        }
        stage('Purge - snapshot') {
            when {
                expression {
                    "${TEST_ONLY}" == "false"
                }
            }
            steps {
                sh 'rm -rf ~/.m2/repository/uk/co/deliverymind/'
            }
        }
        stage('Set snapshot version number') {
            when {
                expression {
                    "${TEST_ONLY}" == "false"
                }
            }
            steps {
                withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                    sh "(cd plugin; mvn versions:set -DnewVersion=${POST_RELEASE_SNAPSHOT_VERSION})"
                    sh "git add -A; git commit -m 'Post-release version bump'"
                }
            }
        }
        stage('Build - snapshot') {
            when {
                expression {
                    "${TEST_ONLY}" == "false"
                }
            }
            steps {
                withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                    sh "(cd plugin; mvn clean install)"
                }
            }
        }
        stage('Integration test - snapshot') {
            when {
                expression {
                    "${TEST_ONLY}" == "false"
                }
            }
            steps {
                withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                    sh "(cd plugin-it; mvn clean verify)"
                    sh "mvn -pl plugin-it clean verify"
                }
            }
        }
        stage('Push release to origin/master') {
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
        stage('Cleanup') {
            steps {
                step([$class: 'WsCleanup'])
            }
        }
    }
}