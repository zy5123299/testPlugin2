package com.example.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleStudyPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        print "Hello Plugin...${project.name}"

        //光定义了Extension类还不行，还必须让project创建出该extension的对象。
        //在Groovy中，XXX.class的类对象引用，可以直接写成XXX
        project.extensions.create('releaseInfo' , ReleaseInfoExtension)

        //光有属性是不够的，你还必须创建扩展任务，让用户对着任务名字去执行该任务，进而完成功能
        //这个task就会去读上面自定义属性中的值，然后干活
        project.tasks.create('releaseInfoTask' , ReleaseInfoTask)
    }

}
