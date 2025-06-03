def codeQuality(){
    stage('Code Quality'){
            echo 'code quality'
        }
    }
def codeChecks(){
if ( BRANCH_NAME == "main" || TAG_NAME ==~ ".*" ) {
    stage('Style Checks') {
        echo 'Style Checks'
    }
    stage('Unit Tests') {
        echo 'Unit Tests'
    }
}}

def artifacts(){
    if ( env.TAG_NAME ==~ ".*" ) {
        stage('Download Dependencies') {
            echo 'Download Dependencies'
        }
        stage('Prepare Artifacts') {
            echo 'Prepare Artifacts'
        }
        stage('Publish Artifacts') {
            echo 'Publish Artifacts'
        }
    }
}