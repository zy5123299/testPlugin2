package com.example.plugin

/**
 * 与自定义Plugin进行参数传递
 */
class ReleaseInfoExtension {

    String versionCode
    String versionName
    String versionInfo
    String fileName

    @Override
    String toString() {
        """ | versionCode = $versionCode
            | versionName = $versionName
            | versionInfo = $versionInfo
            | fileName = $fileName
        """.stripMargin()
    }

}
