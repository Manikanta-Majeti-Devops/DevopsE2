node {
    try {
            // define global variables FOR MAVEN and DOCKER 
        
            // use maven from runtime
            def mavenHome = tool name: 'maven-runtime' , type: 'maven'
        
            // using a variable and mentioning in double-quotes -- interpolation 
            def mavenCMD = "${mavenHome}/bin/mvn" 
        
            // use docker from runtime
            def dockerHome = tool name: 'docker-runtime' , type: 'dockerTool'
        
            // using a variable and mentioning in double-quotes -- interpolation 
            def dockerCMD = "${dockerHome}/bin/docker" 
        
            def ipAddress
        
            stage('git checkout') {
                git credentialsId: 'GithubID', url: 'https://github.com/Manikanta-Majeti-Devops/DevopsE2.git'  
            }
            
                
            stage('code build, test & sonarqube analysis ') {
                // sh 'mvn clean package'
                //sh "${mavenCMD} clean package"
                withSonarQubeEnv('sonar-webhook') {
                    sh "${mavenCMD} clean test package sonar:sonar"
                }
            }
            
            //stage("Quality Gate"){
            //timeout(time: 1, unit: 'MINUTES') {
            //      def qg = waitForQualityGate()
            //      if (qg.status != 'OK') {
            //          error "Pipeline aborted due to quality gate failure: ${qg.status}"
            //        }
            //    }
            //}
        
            stage('docker build'){
            
                //docker build -t mmk4mmk/springboot:1.0 with Build_number
                sh "sudo ${dockerCMD} build -t mmk4mmk/sbt_runtime_doc:1.0.${BUILD_NUMBER} ."
            }
        
            stage('docker run'){
            
                // docker run -- exposed on 8081 which is application port as well 
                sh "sudo ${dockerCMD} run -p 8088:8081 -d mmk4mmk/sbt_runtime_doc:1.0.${BUILD_NUMBER}"
            }
        
            stage('docker push'){
            
                //docker push 
                withCredentials([string(credentialsId: 'dockerhub_pwd', variable: 'dockerHubpassword')]) {
                    sh "sudo ${dockerCMD} login -u mmk4mmk -p ${dockerHubpassword}"
                }
                sh "sudo ${dockerCMD} push mmk4mmk/sbt_runtime_doc:1.0.${BUILD_NUMBER}"
            }   
        
            stage('push to pcf') {
            
                 withCredentials([string(credentialsId: 'pcfpassword', variable: 'pcfpword')]) {
                    sh "cf login -a https://api.run.pivotal.io -u mmk4mmk.mrani@gmail.com -p ${pcfpword} -o devopse2"
                }
                //sh 'cf push sbt_jar -p target/*.jar'
                sh "cf push sbt_runtime_docker --docker-image mmk4mmk/sbt_runtime_doc:1.0.${BUILD_NUMBER}"
            }  
        
            stage('create a new VM instance') {
                ansiblePlaybook 'gcp-instance.yml'
            }
        
            stage('install docker on created GCP VM'){
                sh 'gcloud config set compute/zone us-central1-a'
                sh 'gcloud compute ssh test-vm2 --command="sudo curl -sSL https://get.docker.io/ | sh"'
            }
             
            stage('start docker service on created GCP VM'){
                sh 'gcloud compute ssh test-vm2 --command="sudo service docker start"'
            }
                
            stage('docker version on created GCP VM'){
                sh 'gcloud compute ssh test-vm2 --command="sudo docker --version"'
            }
         
            stage('docker pull on created GCP VM'){
        
                //docker pull
                withCredentials([string(credentialsId: 'dockerhub_pwd', variable: 'dockerHubpassword')]) {
                    sh 'gcloud compute ssh test-vm2 --command="sudo docker login -u mmk4mmk -p ${dockerHubpassword}"'
                }
                sh 'gcloud compute ssh test-vm2 --command="sudo docker pull mmk4mmk/sbt_runtime_doc:1.0.${BUILD_NUMBER}"'
            }   
        
            stage('dokcer run on created GCP VM'){
                sh 'gcloud compute ssh test-vm2 --command="docker run -p 8081:8081 -d mmk4mmk/sbt_runtime_doc:1.0.${BUILD_NUMBER}"'            
            }
        currentBuild.result = 'SUCCESS'     
        }   
        catch (err){
            currentBuild.result = 'FAILURE'
        }
        finally {
             mail to: 'mmk4mmk.mrani@gmail.com',
             subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
             body: "${env.BUILD_URL} has result ${currentBuild.result}"
  }
    }
