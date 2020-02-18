Package com.sreddygundla.devops

// import javaposse.jobdsl.dsl.Job
import javaposse.jobdsl.dsl.DslFactory

class createFolder {
  String parentFolder
  String folderName
  String envVars
  List groups

  def build(DslFactory dslFactory) {
    folderName = parentFolder + "/" + folderName

    dslFactory.folder("${folderName}") {
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
    return folderName;
  }
}
