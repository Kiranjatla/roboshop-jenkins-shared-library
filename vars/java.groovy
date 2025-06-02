def call() {
    pipeline {

        agent any

        stages {
            stage('Compile Code') {
                steps {
                    echo 'Compile Code'
                }
            }
            stage('Code Quality') {
                steps {
                    echo 'code quality'
                    sh 'env'
                }
            }

            stage('Style Checks') {
                when {
                    anyOf {
                        branch 'main'
                        tag "*"
                    }
                }
                steps {
                    echo 'Style Checks'
                }
            }
            stage('Unit Tests') {
                when {
                    anyOf {
                        branch 'main'
                        tag "*"
                    }
                }
                steps {
                    echo 'Unit Tests'
                }
            }
            stage('Build Package') {
                when { tag "*" }
                steps {
                    echo 'Build Package'
                }
            }
            stage('Prepare Artifact') {
                when { tag "*" }
                steps {
                    echo 'Prepare Artifact'
                }
            }
            stage('Publish Artifact') {
                when { tag "*" }
                steps {
                    echo 'Publish Artifact'
                }
            }
        }

    }
}