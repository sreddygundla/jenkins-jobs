import com.sreddygundla.devops.createFolders
import com.sreddygundla.devops.createGitHubOrgFolders

String parentFolder = "sreddygundla"

parentFolder = new createFolder(
  parentFolder: "${parentFolder}",
  folderName: "test",
  envVars: "TESTFOLDER=sreddy\nTESTING=gundla",
  groups: [['group1', 'role1'], ['group2', 'role2']]
).build(this)

new createGitHubOrgFolder(
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
