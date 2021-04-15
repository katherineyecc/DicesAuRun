# DicesAuRun

This README file shows how to run DICES with ONOS and Mininet on Computer Canada Cloud (CCC).
Please read **CCC Setup Tutorial.pdf** before running the scripts in this repo. It will guide you to setup the environment on CCC.  

*Note that You don't need install any softwares or create user on VMs becasue the script will do it automatically. You only need to create the VMs manually on CCC and running the install and activate scripts on the VM after running the DicesAuRun.jar.*  


## Environment:

JDK1.8


## Development tools:

Eclipse


## Setup environment on CCC:

How to setup the environment on CCC?

### Follow the steps in the tutorial

Please follow the instructions in **CCC Setup Tutorial.pdf**.

### Open DicesAuRun folder:

#### Run RunDices.jar: 

Firstly, we use this one to setup all the environments. It will install all the required softwares on the manage machine and mininet machine. It will also create user sdn mininet machine. After that it will try to reconfigure ONOS and start ONOS service on the manage machine. 

We can run it by input the following command:  

`java -jar RunDices.jar`  

Then we input 2 ip addresses (the public network ip for manage machine and the intranet ip for mininet machine) and the location of the public key and private key.  
The source code of RunDices.jar is in **RunDices** folder．  

#### Log into the Manage Machine: 

After running RunDices.jar, the manage machine and mininet machine is all set up. And ONOS service is running on the manage machine. We need to use ssh to log into the manage machine: 

`ssh -i <privateKey> ubuntu@<manageMachineIP>`  

Then we can see 3 folders in the */home/ubuntu* directory: *DicesAuRun* (include scripts and uploaded files), *dices-app* (Jenectics-DICES source code), and *dices-app-ECJ* (ECJ-DICES source code). We need to execute the following commands to install and activate DICES:   

`cd DicesAuRun`

`./installApps.sh`

Note that we cannot run 2 versions of DICES at the same time, so we can only activate one at a time. For example, if we want to activate Jenetics-DICES, we should run the command:

`./activateJenetics.sh`

Before we switch to activating the other one, we need to deactivate the current running DICES by executing:

`./deactivateJenetics.sh`

Similarly, running ECJ-DICES:

`./activateECJ.sh`

And when you finish using it, please remember to deactivate:

`./deactivate.sh`

When all the works are done, we should also stop the ONOS service by running:

`./stopService.sh`

These scripts are all located in */home/ubuntu/DicesAuRun* directory.

#### Run Mininet:

**NOTE: If you use Mininet command *pingall*, this step should be done after activating DICES.**

Basically, we connect to mininet machine by ssh from manage machine. First, create another terminal and ssh to manage machine. Then, run the command:

`ssh sdn@<mininetMachineIP>`

to log into mininet machine. Mininet is installed on the machine. What we need to do is to create a simple topo to test the forwarding ability of DICES by running *pingall*.

`sudo mn --topo=tree,2,3 --mac --controller=remote,ip=<manageMachineIntraIP>,port=6653`

When the topo is created, you should see **mininet>** and you can type in ***pingall***.

After you finish testing by *pingall*, type in ***exit*** to exit from this topo.

**NOTE: Every time we exit, please clean up by: **

`sudo mn -c`
  

## After Mininet pingall:

Please deactivate the DICES app in ONOS using the scripts mentioned above. And run the following commands to stop ONOS service:

`cd /home/ubuntu/DicesAuRun`

`./stopService.sh`

## Interrupted during running DicesRun.jar

If you get interrupted during running DicesRun.jar, please run the following commands to clean up and start over again:

`cd /home/ubuntu/DicesAuRun`

`./Cleanup.sh`

## Toubleshooting

If you have already activated DICES in ONOS but failed in *pingall* (the result of 100% dropped), you should log into ONOS to check if the activation is completed successfully.

`ssh -p 8101 onos@localhost -o StrictHostKeyChecking no`

And it will prompt the password authentication. The password is ***rocks*** by default. Then you can see the ONOS CLI with **onos>**. Please type in:

`log:exception-display`

If the DICES is activated with errors, the error messages will show up.


## Author:

Chuchu Ye
