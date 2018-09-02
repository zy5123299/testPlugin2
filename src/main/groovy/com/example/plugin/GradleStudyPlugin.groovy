package com.example.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleStudyPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        print "Hello Plugin...${project.name}"
    }

}
