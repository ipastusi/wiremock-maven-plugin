#!groovy

folder('wiremock-maven-plugin')

def gitCreds = '11be7a79-8034-407b-8351-dbd1d3342c24'

pipelineJob('wiremock-maven-plugin/build') {
    concurrentBuild(false)
    properties {
        buildDiscarder {
            strategy {
                logRotator {
                    numToKeepStr('10')
                    daysToKeepStr(null)
                    artifactDaysToKeepStr(null)
                    artifactNumToKeepStr(null)
                }
            }
        }
        githubProjectProperty {
            projectUrlStr('https://github.com/deliverymind/wiremock-maven-plugin/')
        }
        rebuildSettings {
            autoRebuild(true)
            rebuildDisabled(false)
        }
    }
    parameters {
        stringParam('RELEASE_VERSION', '3.0.0', '3.1.0, 3.2.0 etc')
        stringParam('POST_RELEASE_SNAPSHOT_VERSION', '3.0.1-SNAPSHOT', '3.1.1-SNAPSHOT, 3.2.1-SNAPSHOT etc')
        nonStoredPasswordParam('GPG_PASSPHRASE', 'GPG passphrase for signing artefacts')
        stringParam('REPO_URL', 'git@github.com:deliverymind/wiremock-maven-plugin.git', 'Or local path, e.g.: "file:///Users/username/git/wiremock-maven-plugin"')
        stringParam('GIT_CREDENTIALS_ID', gitCreds, '')
        stringParam('TEST_ONLY', 'true', '')
        stringParam('DRY_RUN', 'true', '')
    }
    definition {
        cpsScm {
            scm {
                git {
                    branch('*/master')
                    remote {
                        credentials(gitCreds)
                        url('${REPO_URL}')
                    }
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
}
