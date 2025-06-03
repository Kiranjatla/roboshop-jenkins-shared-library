def call() {
    node {

        if (! env.TAG_NAME) {
            env.TAG_NAME = ""
        }
        sh 'env'
        common.codeQuality()
        if ( BRANCH_NAME == "main" || TAG_NAME ==~ ".*" ) {
            stage('Style Checks') {
                echo 'Style Checks'
            }
        }
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