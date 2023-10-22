job('Aplicacion Node.js Docker DSL') {
    description('Aplicación Node JS Docker DSL para el curso de Jenkins')
    scm {
        git('https://github.com/Dante205/nodejsapp.git', 'master') { node ->
            node / gitConfigName('Dante205')
            node / gitConfigEmail('d4nt3x_100@hotmail.com')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('dantedevops/nodejsapp')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('DockerIDdanteaguilar100')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
    publishers {
	slackNotifier {
            notifyAborted(true)
            notifyEveryFailure(true)
            notifyNotBuilt(false)
            notifyUnstable(false)
            notifyBackToNormal(true)
            notifySuccess(true)
            notifyRepeatedFailure(false)
            startNotification(false)
            includeTestSummary(false)
            includeCustomMessage(false)
            customMessage(null)
            sendAs(null)
            commitInfoChoice('NONE')
            teamDomain(null)
            authToken(null)
        }
    }
}
