import Common._

lazy val cortex = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(projectSettings)

libraryDependencies ++= Seq(
  Dependencies.Play.cache,
  Dependencies.Play.ws,
  Dependencies.Play.ahc,
  Dependencies.Play.specs2 % Test,
  Dependencies.Play.guice,
  Dependencies.scalaGuice,
  Dependencies.elastic4play,
  Dependencies.reflections,
  Dependencies.zip4j,
  Dependencies.dockerClient,
  Dependencies.k8sClient,
  Dependencies.akkaCluster,
  Dependencies.akkaClusterTyped
)

resolvers += Resolver.sbtPluginRepo("releases")
resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "elasticsearch-releases" at "https://artifacts.elastic.co/maven"
publishArtifact in (Compile, packageDoc) := false
publishArtifact in packageDoc := false
sources in (Compile, doc) := Seq.empty

// Front-end //
mappings in packageBin in Assets ++= frontendFiles.value

packageBin := {
  (packageBin in Debian).value
  (packageBin in Rpm).value
  (packageBin in Universal).value
}
