
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
          publicRepoPullRequestFilterTrait()
        }
      }
    }

    configure {
      def traits = it / navigators / 'org.jenkinsci.plugins.github__branch__source.GitHubSCMNavigator' / traits
      traits << 'org.jenkinsci.plugins.github_branch_source.BranchDiscoveryTrait' {
          strategyId 1
      }
      traits << 'org.jenkinsci.plugins.github_branch_source.ForkPullRequestDiscoveryTrait' {
          strategyId 2
          trust(class: 'org.jenkinsci.plugins.github_branch_source.ForkPullRequestDiscoveryTrait$TrustEveryone')
      }
      traits << 'org.jenkinsci.plugins.github__branch__source.OriginPullRequestDiscoveryTrait' {
          strategyId 2
      }
    }
    projectFactories {
      workflowMultiBranchProjectFactory {
        // Relative location within the checkout of your Pipeline script.
        scriptPath("Jenkinsfile")
      }
    }
}
