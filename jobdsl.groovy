#!groovy

multibranchPipelineJob('wiremock-maven-plugin') {
    branchSources {
        git {
            remote('git@github.com:deliverymind/wiremock-maven-plugin.git')
            credentialsId('github-creds')
        }
    }
}
