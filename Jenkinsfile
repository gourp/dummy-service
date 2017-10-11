podTemplate(
    inheritFrom: "maven",
    label: "myJenkins",
    cloud: "openshift",
    volumes: [
        persistentVolumeClaim(claimName: "m2repo", mountPath: "/home/jenkins/.m2/")
    ]) {
node ("myJenkins") {

        @Library('github.com/gourp/jenkins-library@master') _
        
        stage ('SCM checkout'){
            echo 'Checking out git repository'
            checkout scm
        }
    
        stage ('Maven build'){
            echo 'Building project'
            sh "mvn package"
        }
    
        stage ('DEV - Image build'){
            echo 'Building docker image and deploying to Dev'
            buildApp('dropwizard-example-dev', "dropwizard-example")
            echo "This is the build number: ${env.BUILD_NUMBER}"
        }
    
        stage ('Automated tests'){
            echo 'This stage simulates automated tests'
            sh "mvn -B -Dmaven.test.failure.ignore verify"
        }
    
        stage ('QA - Promote image'){
            echo 'Deploying to QA'
            promoteImage('dropwizard-example-dev', 'dropwizard-example-qa', 'dropwizard-example', 'latest')
        }
    
        stage ('Wait for approval'){
            input 'Approve to production?'
        }
    
        stage ('PRD - Promote image'){
            echo 'Deploying to production'
            promoteImage('dropwizard-example-qa', 'dropwizard-example', 'dropwizard-example', env.BUILD_NUMBER)
        }

        stage ('PRD - Canary Deploy'){
            echo 'Performing a canary deployment'
            canaryDeploy('dropwizard-example', 'dropwizard-example', env.BUILD_NUMBER)
        }

   }
}
