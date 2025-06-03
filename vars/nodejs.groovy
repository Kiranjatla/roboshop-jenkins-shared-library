def call() {
    node {

        if (! env.TAG_NAME) {
            env.TAG_NAME = ""
        }
        common.codeQuality()
        common.codeChecks()
        common.artifacts()

    }
}