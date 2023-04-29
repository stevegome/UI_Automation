**GEMS HMI Automation SetUp:**

Git Repository:


Automation Run Command



Web 
mvn test -Dplatform=web -Dbrowser=chrome -Dgroups=web -Denv=val30




Run Your testcases on your own testdata by using test data json file

Just include below parameters in your run command and your testcase will be executed with your testdata

-DtestDataJson="path to your testdata json file"



Jenkins and Sauce lab Set up:

Prerequisite:
Jenkins set up has been done and jenkins is up and running.


Installing the OnDemand Plugin

You can install the Sauce OnDemand plugin for Jenkins though the Jenkins Administration page. 

Steps:
In Jenkins, go to the Administration page
Click Manage Jenkins
Click Manage Plugins
Click the Available tab
In the list of plugins, find and select Sauce OnDemand Plugin
Click Download now and install after restart
In the plugin installation dialog, select Restart Jenkins when installation is complete and no jobs are running




