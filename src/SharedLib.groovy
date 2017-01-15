// Jenkins Pipeline Plugin Folder-Level Shared Library

def prepare() {
    step([$class: 'WsCleanup'])
    git credentialsId: "${GIT_CREDENTIALS_ID}", url: "${REPO_URL}"
}

def purge() {
    withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
        sh 'mvn -pl plugin dependency:purge-local-repository -DresolutionFuzziness=groupId -Dinclude=uk.co.deliverymind -DactTransitively=false -DreResolve=false -Dverbose=true'
    }
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
