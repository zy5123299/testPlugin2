package com.example.plugin

import groovy.xml.MarkupBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.util.TextUtil

/**
 * 默认创建的Task都是继承与Task
 */

/**
 * 自定义Task，实现维护版本信息的功能
 */
class ReleaseInfoTask extends DefaultTask {

    ReleaseInfoTask() {
        group = 'test plugin app'
        description = 'update the release info'
    }

    /**
     * Task在Gradle执行阶段所执行的逻辑，都在这里面
     * doFirst和doLast，会在其执行前和执行后，来执行
     */
    @TaskAction
    void doAction() {
        //do first
        //do something
        //do last

        updateInfo()
    }

    //真正的将Extension类中的信息，写入指定的文件中（FileName）
    private void updateInfo() {
        //获取将要写入的信息
        ReleaseInfoExtension releaseInfoExtension = project.extensions.releaseInfo//讲道理，这里还是先用find函数找一下比较好···
        String versionCode = releaseInfoExtension.versionCode
        String versionName = releaseInfoExtension.versionName
        String versionInfo = releaseInfoExtension.versionInfo
        String fileName = releaseInfoExtension.fileName

        File file = project.file(fileName)

        //将文件Info写入到指定文件中
        def sw = new StringWriter()
        def xmlBuilder = new MarkupBuilder(sw)

        xmlBuilder.releases {
            release {
                versionCode(versionCode)
                versionName(versionName)
                versionInfo(versionInfo)
            }
        }

        if (!file.exists()) {
            file.createNewFile()
        }

        //没内容 -> 直接写入
        if (file.text.size() <= 0) {
            file.withWriter {
                writer ->
                    writer.append(sw.toString())
            }
        }
        //有内容 -> 跟在倒数第二行
        else {
            def lines = file.readLines()
            def destLine = lines.size() - 1
            file.withWriter {
                writer ->
                    lines.eachWithIndex{ line , index ->
                        if (index != destLine) {
                            writer.append(line + '\r\n')
                        }else {
                            writer.append('\r\n' + sw.toString() + '\r\n')
                            writer.append(lines.last())
                        }
                    }
            }
        }
    }

}
