#!groovy

multibranchPipelineJob('wiremock-maven-plugin') {
    branchSources {
        git {
            remote('git@github.com:automatictester/wiremock-maven-plugin.git')
            credentialsId('github-creds')
        }
    }
}
