import com.sreddygundla.devops.createFolders
import com.sreddygundla.devops.createGitHubOrgFolders

String parentFolder = "sreddygundla"

parentFolder = new createFolders(
  parentFolder: "${parentFolder}",
  folderName: "test",
  envVars: "TESTFOLDER=sreddy\nTESTING=gundla",
  groups: [['group1', 'role1'], ['group2', 'role2']]
).build(this)

new createGitHubOrgFolders(
  parentFolder: "${parentFolder}",
  folderName: "testScanner",
  orgName: "sreddygundla",
  repoRegexp: ".*",
  branchInclude: "*",
  branchExclude: "master",
  branchesAutoTrigger: "feature.*",
  envVars: "TESTFOLDER=sreddy\nTESTING=gundla",
  groups: [['group1', 'role1'], ['group2', 'role2']]
).build(this)
