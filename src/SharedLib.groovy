// Jenkins Pipeline Plugin Folder-Level Shared Library

def prepare() {
    step([$class: 'WsCleanup'])
    git credentialsId: "${GIT_CREDENTIALS_ID}", url: "${REPO_URL}"
}

def purge() {
    sh 'rm -rf ~/.m2/repository/uk/co/deliverymind/'

//    does not work correctly in all cases
//    withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
//        sh 'mvn -pl plugin dependency:purge-local-repository -DresolutionFuzziness=groupId -Dinclude=uk.co.deliverymind -DactTransitively=false -DreResolve=false -Dverbose=true'
//    }
}

def build() {
    withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
        sh "(cd plugin; mvn clean install)"
    }
}

def runITs() {
    withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
        sh "(cd plugin-it; mvn clean verify)"
        sh "mvn -pl plugin-it clean verify"
    }
}

def tag() {
    sh "git tag ${RELEASE_VERSION}"
}

def release() {
    withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
        sh "(cd plugin; mvn clean deploy -P release -Dgpg.passphrase=${GPG_PASSPHRASE})"
    }
}

def commitReleaseVersion() {
    withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
        sh "(cd plugin; mvn versions:set -DnewVersion=${RELEASE_VERSION})"
        sh "git add -A; git commit -m 'Release version bump'"
    }
}

def commitSnapshotVersion() {
    withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
        sh "(cd plugin; mvn versions:set -DnewVersion=${POST_RELEASE_SNAPSHOT_VERSION})"
        sh "git add -A; git commit -m 'Post-release version bump'"
    }
}

def push() {
    sshagent(["${GIT_CREDENTIALS_ID}"]) {
        sh "git push --set-upstream origin master; git push --tags"
    }
}
