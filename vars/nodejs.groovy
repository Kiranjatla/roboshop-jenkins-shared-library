def call() {
    env.APPTYPE == "nodejs"
    node {
        try {
            common.codeCheckout()
            common.codeQuality()
            common.codeChecks()
            common.artifacts()

    } catch (Exception e){
            mail bcc: '', body: 'Build Failure ${RUN_DISPLAY_URL}', cc: '', from: 'kiranjatla9@gmail.com', replyTo: '', subject: 'Build failed', to: 'kiranjatla9@gmail.com'
        }
    }
}