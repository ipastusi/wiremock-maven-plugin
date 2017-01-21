#!groovy

def sharedLib = new SharedLib()

node {
    stage('Prepare') {
        sharedLib.prepare()
        sharedLib.purge()
    }
    stage('Set release version number') {
        if ("${TEST_ONLY}" == "false") {
            sharedLib.commitReleaseVersion()
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
            sharedLib.tag()
        }
    }
    stage('Release artefacts') {
        if ("${TEST_ONLY}" == "false" && "${DRY_RUN}" == "false") {
            sharedLib.release()
        }
    }
    stage('Purge') {
        if ("${TEST_ONLY}" == "false") {
            sharedLib.purge()
        }
    }
    stage('Set snapshot version number') {
        if ("${TEST_ONLY}" == "false") {
            sharedLib.commitSnapshotVersion()
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
            sharedLib.push()
        }
    }
}