def call() {
    env.SONAR_EXTRA_OPTS = "-Dsonar.java.binaries=./target"
    env.APPTYPE = "java"
    node {
        try {
            common.codeCheckout()
            stage('Compile Code') {
                sh 'mvn compile'
            }
            common.codeQuality()
            common.codeChecks()
            common.artifacts()
        } catch (Exception e){
            mail bcc: '', body: "Build Failure ${RUN_DISPLAY_URL}", cc: '', from: 'kiranjatla9@gmail.com', replyTo: '', subject: 'Build failed', to: 'kiranjatla9@gmail.com'
        }
    }
}








//def call() {
//    pipeline {
//
//        agent any
//
//        stages {
//            stage('Compile Code') {
//                steps {
//                    echo 'Compile Code'
//                }
//            }
//            stage('Code Quality') {
//                steps {
//                    echo 'code quality'
//                    sh 'env'
//                }
//            }
//
//            stage('Style Checks') {
//                when {
//                    anyOf {
//                        branch 'main'
//                        tag "*"
//                    }
//                }
//                steps {
//                    echo 'Style Checks'
//                }
//            }
//            stage('Unit Tests') {
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
//            stage('Build Package') {
//                when { tag "*" }
//                steps {
//                    echo 'Build Package'
//                }
//            }
//            stage('Prepare Artifact') {
//                when { tag "*" }
//                steps {
//                    echo 'Prepare Artifact'
//                }
//            }
//            stage('Publish Artifact') {
//                when { tag "*" }
//                steps {
//                    echo 'Publish Artifact'
//                }
//            }
//        }
//
//    }
//}