package com.sreddygundla.devops

// import javaposse.jobdsl.dsl.Job
import javaposse.jobdsl.dsl.DslFactory
class createFolder {
  String parentFolder
  String folderName
  String envVars
  List groups

  def build(DslFactory dslFactory) {
    foldName = parentFolder + "/" + folderName

    dslFactory.folder("${foldName}") {
      displayName("${folderName}")
      properties {
        // envVarsFolderProperty {
        //   properties("${envVars}")
        // }
        // folderProxyGroupContainer {
        //
        // }
      }
    }
    return foldName;
  }
}
