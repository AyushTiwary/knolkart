lazy val common = Seq {
  name := "Knolkart"

  version := "1.0"

  scalaVersion := "2.12.3"

  libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test"
}


lazy val inventory = (project in file("AccessInventory"))
  .settings(
    common
  )

lazy val checkoutservice = (project in file("checkout"))
  .settings(
    common
  )
lazy val inventorysdk = (project in file("inventory"))
  .settings(
    common
  )
lazy val notificationservice = (project in file("notification"))
  .settings(
    common
  )


lazy val root = project.in(file(".")).aggregate(inventory,checkoutservice, inventorysdk, notificationservice)