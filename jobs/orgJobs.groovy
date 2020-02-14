
folder("/sgundla") {
  displayName('sgundla')
  description("sgundla workspace")
}

organizationFolder("/sgundla/git-org-scanner") {
    description('This contains branch source jobs for GitHub')
    displayName('Organization Folder')
    triggers {
        periodic(5)
    }

    organizations {
      github {
        repoOwner("sreddygundla")
        apiUri("https://api.github.com")
        credentialsId('github-token')
        traits {
          //publicRepoPullRequestFilterTrait()
        }
      }
    }

    configure {
      def traits = it / navigators / 'org.jenkinsci.plugins.github__branch__source.GitHubSCMNavigator' / traits
      traits << 'jenkins.scm.impl.trait.RegexSCMSourceFilterTrait plugin="scm-api@2.6.3"' {
          regex '.*'
      }
      traits << 'org.jenkinsci.plugins.github__branch__source.BranchDiscoveryTrait' {
          strategyId 3
      }
      traits << 'jenkins.scm.impl.trait.WildcardSCMHeadFilterTrait plugin="scm-api@2.6.3"' {
          excludes 'master'
      }
    }
    projectFactories {
      workflowMultiBranchProjectFactory {
        // Relative location within the checkout of your Pipeline script.
        scriptPath("Jenkinsfile")
      }
    }
    properties {
      noTriggerOrganizationFolderProperty {
        branches('feature.*')
      }
    }
}
