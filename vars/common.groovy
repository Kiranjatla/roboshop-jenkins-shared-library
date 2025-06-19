def codeCheckout() {
    stage('Code Checkout') {
        sh 'find . | sed 1d |xargs rm -rf'
        git branch: 'main', url: "https://github.com/Kiranjatla/${COMPONENT}.git"
    }
}
def codeQuality(){
    stage('Code Quality') {
        withCredentials([usernamePassword(credentialsId: 'SONAR', passwordVariable: 'sonarPass', usernameVariable: 'sonarUser')]) {
            sh '''
               #sonar-scanner -Dsonar.host.url=http://172.31.39.42:9000 -Dsonar.login=${sonarUser} -Dsonar.password=${sonarPass} -Dsonar.projectKey=${COMPONENT} -Dsonar.qualitygate.wait=true ${SONAR_EXTRA_OPTS}
               echo ok
              '''
        }
    }
    }
def codeChecks(){
    if ( env.BRANCH_NAME == "main" || env.TAG_NAME ==~ ".*" ) {
    stage('Style & Lint Checks') {
        echo 'Style Checks'
    }
    stage('Unit Tests') {
        echo 'Unit Tests'
    }
}}

def artifacts() {
    if (env.TAG_NAME ==~ ".*") {
        stage('Prepare Artifacts') {
            if (env.APPTYPE == "nodejs") {
                sh """
                      echo "Installing node dependencies"
                      npm install
                       echo "Zipping node_modules and server.js to ${COMPONENT}-${TAG_NAME}.zip"
                       zip -r ${COMPONENT}-${TAG_NAME}.zip node_modules server.js
                    """
            }

            if (env.APPTYPE == "java") {
                sh """
                      echo "Building Java application"
                       mvn clean package

                        echo "Renaming jar to ${COMPONENT}.jar"
                        mv target/${COMPONENT}-1.0.jar ${COMPONENT}.jar

                         echo "Zipping ${COMPONENT}.jar into ${COMPONENT}-${TAG_NAME}.zip"
                         zip -r ${COMPONENT}-${TAG_NAME}.zip ${COMPONENT}.jar
                    """
            }

            if (env.APPTYPE == "python") {
                sh """
                      echo "Zipping Python files: *.py, ${COMPONENT}.ini, requirements.txt"
                      zip -r ${COMPONENT}-${TAG_NAME}.zip *.py ${COMPONENT}.ini requirements.txt
                    """
            }

            if (env.APPTYPE == "nginx") {
                sh '''
                      echo "Zipping static files for nginx"
                      cd static
                      zip -r ../${COMPONENT}-${TAG_NAME}.zip *
                   '''
            }

        }
        stage('Publish Artifacts') {
            withCredentials([usernamePassword(credentialsId: 'NEXUS', passwordVariable: 'nexusPass', usernameVariable: 'nexusUser')]) {
                sh '''
                      curl -v -u ${nexusUser}:${nexusPass} --upload-file ${COMPONENT}-${TAG_NAME}.zip http://nexus.roboshop.internal:8081/repository/${COMPONENT}/${COMPONENT}-${TAG_NAME}.zip
                   '''
            }
        }
    }
}