
folder("/sgundla") {
  displayName('sgundla')
  description("sgundla workspace")
}

organizationFolder("/sgundla/git-org-scanner") {
    description('This contains branch source jobs for GitHub')
    displayName('Organization Folder')
    triggers {
        periodic(3)
    }
}
