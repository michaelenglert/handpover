# AppDynamics Handover Tool

* Simplify the data gathering after a Proof of Value
* Single Executable
* Java 1.6 and above

## Download and run

1. [Download](https://drive.google.com/file/d/0BwPLxa1Qpa9MUS15ZVRLUXh5Vjg/view?usp=sharing)
2. `java -jar <Downloaded.jar>`

## Build yourself

If you want to build the package by yourself

1. `git clone https://github.com/michaelenglert/handpover.git`
2. `cd handpover`
3. `mvn clean compile assembly:single`
4. `cd target`
5. `java -jar PoV-Handover-Tool-<Version>-jar-with-dependencies.jar`

## Current Capabilities

* Excel Export
 * Controller Settings
 * Controller Audit
 * Apps
 * BTs
 * Tiers
 * Nodes
* XML
 * Application Configs
* Logs
 * Controller Logs (not for SaaS)
* JSON
 * Dashboards
