node {
        stage('git checkout') {
            git credentialsId: 'GithubID', url: 'https://github.com/Manikanta-Majeti-Devops/DevopsE2.git'  
    }
    
    stage('code build & test') {
        // sh 'mvn clean package'
        
        // use maven from runtime
        
        def mavenHome = tool name: 'maven-runtime' , type: 'maven'
        
        // using a variable and mentioning in double-quotes -- interpolation 
        def mavenCMD = "${mavenHome}/bin/mvn" 
        
        sh "${mavenCMD} clean package"
    }
    
    stage('docker build'){
        
        // use docker from runtime
        
        def dockerHome = tool name: 'docker-runtime' , type: 'dockerTool'
        
        // using a variable and mentioning in double-quotes -- interpolation 
        def dockerCMD = "${dockerHome}/bin/docker" 
        
        // use docker available on machine 
        
        sh "sudo ${dockerCMD} build -t mmk4mmk/sbt_runtime_doc:1.0 ."
    }
    
    stage('docker run'){
        
        // use docker from runtime
        
        def dockerHome = tool name: 'docker-runtime' , type: 'dockerTool'
        
        // using a variable and mentioning in double-quotes -- interpolation 
        def dockerCMD = "${dockerHome}/bin/docker"
        
        sh "sudo ${dockerCMD} run -p 8087:8081 -d mmk4mmk/sbt_runtime_doc:1.0"
    }
    
    stage('docker push'){
        
        // use docker from runtime
        
        def dockerHome = tool name: 'docker-runtime' , type: 'dockerTool'
        
        // using a variable and mentioning in double-quotes -- interpolation 
        def dockerCMD = "${dockerHome}/bin/docker"
        
        //docker push 
        withCredentials([string(credentialsId: 'dockerhub_pwd', variable: 'dockerHubpassword')]) {
                // some block
                sh "sudo ${dockerCMD} login -u mmk4mmk -p ${dockerHubpassword}"
            }
        sh "sudo ${dockerCMD} push mmk4mmk/sbt_runtime_doc:1.0"
    }
    
    stage('push to pcf') {
        
         withCredentials([string(credentialsId: 'pcfpassword', variable: 'pcfpword')]) {
    // some block

            sh "cf login -a https://api.run.pivotal.io -u mmk4mmk.mrani@gmail.com -p ${pcfpword} -o devopse2"
        }
        
        sh 'cf push sbt_runtime_docker --docker-image mmk4mmk/sbt_runtime_doc:1.0 --no-start'
    }
}
