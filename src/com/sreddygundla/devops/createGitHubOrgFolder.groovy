Package com.sreddygundla.devops

// import javaposse.jobdsl.dsl.Job
import javaposse.jobdsl.dsl.DslFactory

public class createGitHubOrgFolder {
  String parentFolder
  String folderName
  String orgName
  String repoRegexp
  String branchInclude
  String branchExclude
  String branchesAutoTrigger
  String envVars
  List groups

  def build(DslFactory dslFactory) {
    String jenkinsFilePath = 'JenkinsFile'
    String gitHubCredentialsId = 'github-token'
    String aigGitHubAPI = 'https://api.github.com'
    folderName = parentFolder + "/" + folderName

    dslFactory.organizationFolder("${folderName}") {
      displayName("${folderName}")
      triggers {
        periodic(1440)
      }
      organizations {
        github {
          repoOwner("${orgName}")
          apiUri("${aigGitHubAPI}")
          credentialsId("${gitHubCredentialsId}")
          traits {
            //publicRepoPullRequestFilterTrait()
          }
        }
      }
      configure {
        def traits = it / navigators / 'org.jenkinsci.plugins.github__branch__source.GitHubSCMNavigator' / traits
        traits << 'jenkins.scm.impl.trait.RegexSCMSourceFilterTrait' {
          regex "${repoRegexp}"
        }
        traits << 'org.jenkinsci.plugins.github__branch__source.BranchDiscoveryTrait' {
          strategyId 3
        }
        traits << 'jenkins.scm.impl.trait.WildcardSCMHeadFilterTrait' {
          includes "${branchInclude}"
          excludes "${branchExclude}"
        }
      }
      projectFactories {
        workflowMultiBranchProjectFactory {
          // Relative location within the checkout of your Pipeline script.
          scriptPath("${jenkinsFilePath}")
        }
      }
      properties {
        noTriggerOrganizationFolderProperty {
          branches("${branchesAutoTrigger}")
        }
        // envVarsFolderProperty {
        //   properties("${envVars}")
        // }
        // folderProxyGroupContainer {
        //
        // }
      }
    }
  }
}
