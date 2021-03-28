package com.github.joselion.strictnullcheck

import java.util.List

import org.gradle.api.Project
import org.gradle.api.file.ProjectLayout
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

public class StrictNullCheckExtension {

  final ListProperty<String> annotations

  final Property<String> generatedDir

  final Property<String> packageJavadoc

  final Property<Versions> versions

  StrictNullCheckExtension(ObjectFactory objects, ProjectLayout layout) {
    this.annotations = objects.listProperty(String)
    this.generatedDir = objects.property(String)
    this.packageJavadoc = objects.property(String)
    this.versions = objects.property(Versions)

    this.annotations.convention(['org.eclipse.jdt.annotation.NonNullByDefault'])
    this.generatedDir.convention(layout.buildDirectory.get().asFile.path + '/generated')
    this.packageJavadoc.convention('')
    this.versions.convention(new Versions(objects))
  }

  public void useSpring() {
    this.annotations.convention([
      'org.springframework.lang.NonNullApi',
      'org.springframework.lang.NonNullFields'
    ])
  }

  public static class Versions {

    final Property<String> eclipseAnnotations

    final Property<String> findBugs

    Versions(ObjectFactory objects) {
      this.eclipseAnnotations = objects.property(String)
      this.findBugs = objects.property(String)

      this.eclipseAnnotations.convention('2.2.600')
      this.findBugs.convention('3.0.2')
    }
  }
}
