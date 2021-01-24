#!groovy

multibranchPipelineJob('wiremock-maven-plugin') {
    branchSources {
        git {
            id('AD8DE9A4-79E2-4C8B-A4E9-3F59E39EB3C7')
            remote('git@github.com:automatictester/wiremock-maven-plugin.git')
            credentialsId('github-creds')
        }
    }
}
