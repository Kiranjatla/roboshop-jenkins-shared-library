def call() {
    node {
        sh 'env'
     common.codeQuality()
    }
    }
 if( branch == "main" || tag ==~ ".*"){
        stage('Style Checks'){
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
