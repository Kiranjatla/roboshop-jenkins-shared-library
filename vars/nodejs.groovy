def call() {
    node {

        if (! env.TAG_NAME) {
            env.TAG_NAME = ""
        }
        sh 'env'
        common.codeQuality()
        common.codeChecks()
        common.artifacts()

//            stage('Unit Tests'){
//                when {
//                    anyOf {
//                        branch 'main'
//                        tag "*"
//                    }
//                }
//                steps {
//                    echo 'Unit Tests'
//                }
//            }
//            stage('Prepare Artifact'){
//                when { tag "*" }
//                steps {
//                    echo 'Prepare Artifact'
//                }
//            }
//            stage('Publish Artifact'){
//                when { tag "*" }
//                steps {
//                    echo 'Publish Artifact'
//                }
//            }
//        }
    }
}