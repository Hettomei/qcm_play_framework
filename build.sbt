name := "QCM_play"

version := "1.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.hibernate" % "hibernate-entitymanager" % "4.1.1.Final",
  "org.hibernate" % "hibernate-core" % "4.1.1.Final",
  "org.hibernate" % "hibernate-validator" % "4.2.0.Final"
)

libraryDependencies += filters

play.Project.playJavaSettings
