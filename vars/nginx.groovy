def call() {
    env.APPTYPE = "nginx"
    node {
        try {
            common.codeCheckout()
            common.codeQuality()
            common.codeChecks()
            common.artifacts()
        } catch (Exception e){
            mail bcc: '', body: "Build Failure ${RUN_DISPLAY_URL}", cc: '', from: 'kiranjatla9@gmail.com', replyTo: '', subject: 'Build failed', to: 'kiranjatla9@gmail.com'
        }
    }
}





//def call(){
//    pipeline{
//
//        agent any
//
//        stages{
//            stage('Code Quality'){
//                steps{
//                    echo 'code quality'
//                    sh 'env'
//                }
//            }
//
//            stage('Style Checks'){
//                when{
//                    anyOf{
//                        branch 'main'
//                        tag "*"
//                    }
//                }
//                steps{
//                    echo 'Style Checks'
//                }
//            }
//            stage('Unit Tests'){
//                when{
//                    anyOf{
//                        branch 'main'
//                        tag "*"
//                    }
//                }
//                steps{
//                    echo 'Unit Tests'
//                }
//            }
//            stage('Prepare Artifact'){
//                when{ tag "*" }
//                steps{
//                    echo 'Prepare Artifact'
//                }
//            }
//            stage('Publish Artifact'){
//                when{ tag "*" }
//                steps{
//                    echo 'Publish Artifact'
//                }
//            }
//        }
//
//    }
//
//}