import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Scanner;

public class main {

	//Set up log information
	public static Log LOG = LogFactory.getLog(main.class);
	
	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the ip address for manage machine: ");
		String manageMachineIP = input.next();
		
		System.out.println("Please enter the ip address for mininet machine: ");
		String mininetMachineIP = input.next();
		
		System.out.println("Please enter the absolute file path of your private key: ");
		String privatekeyLocation = input.next();
		
		System.out.println("Please enter the absolute file path of your public key: ");
		String publickeyLocation = input.next();
		
		String projectPath = ShellUtils.getrelativePath();
		
		ShellUtils.execShell("cp " + privatekeyLocation + " " + projectPath + "/id_rsa");
		ShellUtils.execShell("cp " + publickeyLocation + " " + projectPath + "/id_rsa.pub");
		
		privatekeyLocation = projectPath + "/id_rsa";
		publickeyLocation = projectPath + "/id_rsa.pub";
		
		SshServerUtils sshObj = new SshServerUtils();

		
		//Connect to manage machine
		if (!(sshObj.connect("ubuntu", "", manageMachineIP, 22, privatekeyLocation))) {
			LOG.error("Check your ip address or username.");
		}
		//Upload the private key and public key to the manage machine
		sshObj.sftpUpload("/home/ubuntu/.ssh", privatekeyLocation);
		sshObj.sftpUpload("/home/ubuntu/.ssh", publickeyLocation);
		
		//Upload files
		sshObj.execCmd("mkdir DicesAuRun");
		sshObj.execCmd(Constants.installJava11);
        sshObj.execCmd(Constants.installJava8);
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/SetUpMininetMachine.jar");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/pshecj.jar");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/ecj-26.jar");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/setenv.sh");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/org.ops4j.pax.url.mvn.cfg.sh");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/installApps.sh");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/activateJenetics.sh");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/activateECJ.sh");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/deactivateJenetics.sh");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/deactivateECJ.sh");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/stopService.sh");
        sshObj.sftpUpload("/home/ubuntu/DicesAuRun", projectPath + "/Cleanup.sh");
        
        sshObj.closeSession();
        
        sshObj.connect("ubuntu", "", manageMachineIP, 22, privatekeyLocation);
        
        sshObj.execCmd("cd /home/ubuntu/DicesAuRun; "
        				+ "sudo mv setenv.sh setenv; "
        				+ "sudo mv org.ops4j.pax.url.mvn.cfg.sh org.ops4j.pax.url.mvn.cfg");
        sshObj.shellCmd("cd /home/ubuntu/DicesAuRun; "
        		+ "sudo chmod 777 installApps.sh; "
        		+ "sudo chmod +x installApps.sh; "
        		+ "sudo chmod 777 stopService.sh; "
        		+ "sudo chmod +x stopService.sh; "
        		+ "sudo chmod 777 activateJenetics.sh; "
        		+ "sudo chmod +x activateJenetics.sh; "
        		+ "sudo chmod 777 activateECJ.sh; "
        		+ "sudo chmod +x activateECJ.sh; "
        		+ "sudo chmod 777 deactivateJenetics.sh; "
        		+ "sudo chmod +x deactivateJenetics.sh; "
        		+ "sudo chmod 777 deactivateECJ.sh; "
        		+ "sudo chmod +x deactivateECJ.sh; "
        		+ "sudo chmod 777 Cleanup.sh; "
        		+ "sudo chmod +x Cleanup.sh");
        sshObj.closeSession();
        
        sshObj.connect("ubuntu", "", manageMachineIP, 22, privatekeyLocation);
        
        
        //Set up manage machine    
        //Install softwares on the manage machine
        sshObj.execCmd(Constants.update);
        sshObj.execCmd(Constants.upgrade);
        sshObj.execCmd(Constants.installgit);
        sshObj.execCmd(Constants.installzip);
        sshObj.execCmd(Constants.installunzip);
        sshObj.execCmd(Constants.installcurl);
        sshObj.execCmd(Constants.installpy2);
        sshObj.execCmd(Constants.installpy3);
        sshObj.execCmd(Constants.installpip);
        sshObj.execCmd(Constants.installselenium);
        sshObj.execCmd(Constants.installrequests);
        sshObj.execCmd(Constants.installmaven);
        
        //Git DICES app
        sshObj.execCmd(Constants.gitJeneticsDices);
        sshObj.execCmd(Constants.gitEcjDices);
        
        //Git ONOS
        sshObj.execCmd(Constants.wgetOnos);
        sshObj.execCmd(Constants.unzipOnos);
        sshObj.execCmd(Constants.cpOnos);
        sshObj.execCmd(Constants.rmOnosTar);
        sshObj.shellCmd("sudo rm /home/ubuntu/onos/apache-karaf-3.0.8/bin/setenv; "
        		+ "sudo cp /home/ubuntu/DicesAuRun/setenv /home/ubuntu/onos/apache-karaf-3.0.8/bin/setenv; "
        		+ "sudo chmod +x /home/ubuntu/onos/apache-karaf-3.0.8/bin/setenv; "
        		+ "sudo rm /home/ubuntu/onos/apache-karaf-3.0.8/etc/org.ops4j.pax.url.mvn.cfg; "
        		+ "sudo cp /home/ubuntu/DicesAuRun/org.ops4j.pax.url.mvn.cfg /home/ubuntu/onos/apache-karaf-3.0.8/etc/org.ops4j.pax.url.mvn.cfg; "
        		+ "sudo chmod +x /home/ubuntu/onos/apache-karaf-3.0.8/etc/org.ops4j.pax.url.mvn.cfg");
        
        sshObj.closeSession();
        
        sshObj.connect("ubuntu", "", manageMachineIP, 22, privatekeyLocation);
  		
  		//Maven install Jenetics-DICES
        sshObj.shellCmd("cd " + Constants.jeneticsDicesPath + "; "
        		+ "mvn clean install");
        
  		//Maven install ECJ-DICES
  		sshObj.shellCmd("cd " + Constants.EcjDicesPath + "; "
  				+ "sudo cp /home/ubuntu/DicesAuRun/pshecj.jar " + Constants.EcjDicesPath + "; "
  				+ "sudo cp /home/ubuntu/DicesAuRun/ecj-26.jar " + Constants.EcjDicesPath + "; "
  				+ "mvn install:install-file -Dfile=ecj-26.jar -Dpackaging=jar -DgroupId=edu.gmu.cs -DartifactId=pshecj -Dversion=26; "
  				+ "mvn install:install-file -Dfile=pshecj.jar -Dpackaging=jar -DgroupId=edu.gmu.cs -DartifactId=pshecj -Dversion=1.0; "
  				+ "mvn clean install");
        
        System.out.println("After setup manage machine");
 
        
        //Set up mininet machine
        sshObj.shellCmd("cd ~/DicesAuRun; java -jar SetUpMininetMachine.jar ubuntu " + mininetMachineIP);
        System.out.println("After setup mininet machine");
        
        
		sshObj.shellCmd("if [ -f \"/home/ubuntu/.ssh/known_hosts\" ]; then sudo rm /home/ubuntu/.ssh/known_hosts; fi");
        sshObj.shellCmd("if [ -f \"/home/ubuntu/.ssh/known_hosts.old\" ]; then sudo rm /home/ubuntu/.ssh/known_hosts.old; fi");
        
        sshObj.closeSession();
        
        sshObj.connect("ubuntu", "", manageMachineIP, 22, privatekeyLocation);
        
        //Run the ONOS service
        sshObj.shellCmd("cd /home/ubuntu/onos/bin; "
        		+ "sudo ./onos-service server &");
        
        sshObj.closeSession();
       
	}
}
