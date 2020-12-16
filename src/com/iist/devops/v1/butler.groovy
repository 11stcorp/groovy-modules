#!/usr/bin/groovy
package com.iist.devops.v1

def prepare(name = 'sample', version = '') {
    // image name
    this.name = name

    echo "# name: ${name}"

    set_version(version)
}

def set_version(version = '') {
    // version
    if (!version) {
        date = (new Date()).format('yyyyMMdd-HHmm')
        version = "v0.0.${date}"
    }

    this.version = version

    echo "# version: ${version}"
}

def get_version() {
    if (!version) {
        throw new RuntimeException('No version')
    }
    echo "# version: ${version}"
    this.version
}

def aws_codebuild(  pProjectName,
                    pEnvVariables,
                    pCredentialsId = 'codebuild-user',
                    pCredentialsType = 'jenkins',
                    pSourceControlType = 'jenkins',
                    defaultRegion = 'ap-northeast-2'
                    ) {
    awsCodeBuild  credentialsId: pCredentialsId,
                  credentialsType: pCredentialsType,
                  sourceControlType: pSourceControlType,
                  projectName: pProjectName,
                  region: defaultRegion,
                  envVariables: pEnvVariables
}
