#!groovy

def sharedLib = new SharedLib()

node {
    stage('Prepare') {
        sharedLib.prepare()
        sharedLib.purge()
    }
    stage('Set release version number') {
        if ("${TEST_ONLY}" == "false") {
            withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                sh "(cd plugin; mvn versions:set -DnewVersion=${RELEASE_VERSION})"
                sh "git add -A; git commit -m 'Release version bump'"
            }
        }
    }
    stage('Build') {
        sharedLib.build()
    }
    stage('Integration test') {
        sharedLib.runITs()
    }
    stage('Tag release') {
        if ("${TEST_ONLY}" == "false") {
            sh "git tag ${RELEASE_VERSION}"
        }
    }
    stage('Release artefacts') {
        if ("${TEST_ONLY}" == "false" && "${DRY_RUN}" == "false") {
            withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                sh "(cd plugin; mvn clean deploy -P release -Dgpg.passphrase=${GPG_PASSPHRASE})"
            }
        }
    }
    stage('Purge') {
        if ("${TEST_ONLY}" == "false") {
            sharedLib.purge()
        }
    }
    stage('Set snapshot version number') {
        if ("${TEST_ONLY}" == "false") {
            withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
                sh "(cd plugin; mvn versions:set -DnewVersion=${POST_RELEASE_SNAPSHOT_VERSION})"
                sh "git add -A; git commit -m 'Post-release version bump'"
            }
        }
    }
    stage('Build - snapshot') {
        if ("${TEST_ONLY}" == "false") {
            sharedLib.build()
        }
    }
    stage('Integration test - snapshot') {
        if ("${TEST_ONLY}" == "false") {
            sharedLib.runITs()
        }
    }
    stage('Push release to origin/master') {
        if ("${TEST_ONLY}" == "false" && "${DRY_RUN}" == "false") {
            sshagent(["${GIT_CREDENTIALS_ID}"]) {
                sh "git push --set-upstream origin master; git push --tags"
            }
        }
    }
}