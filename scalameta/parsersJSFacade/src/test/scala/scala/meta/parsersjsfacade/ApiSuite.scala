package scala.meta.tests
package parsersjsfacade

import org.scalatest._
import scala.scalajs.js
import scala.meta.parsersjsfacade._
import scala.collection.mutable

class ApiSuite extends FunSuite {

  private[this] val d = js.Dictionary
  private[this] val a = js.Array
  private[this] def pos(s: Int, e: Int) = d("start" -> s, "end" -> e)

  private[this] def toJSON(a: js.Dictionary[Any]): String =
    js.JSON.stringify(a.asInstanceOf[js.Any], space = 2)

  private[this] def check(a: js.Dictionary[Any], b: js.Dictionary[Any]) = {
    assert(toJSON(a) == toJSON(b))
  }

  test("Api") {
    val code =
      s"""|object Main {
          |  def main(args: Array[String]): Unit =
          |    println("Hello, World")
          |}""".stripMargin
    val parsed = ScalaParser.parse(code)
    val expected = d(
      "type" -> "Source",
      "children" -> a(
        d(
          "type" -> "Defn.Object",
          "children" -> a(
            d(
              "type" -> "Term.Name",
              "children" -> a(),
              "pos" -> pos(7, 11),
              "value" -> "Main"
            ),
            d(
              "type" -> "Template",
              "children" -> a(
                d(
                  "type" -> "Term.Param",
                  "children" -> a(
                    d(
                      "type" -> "Name.Anonymous",
                      "children" -> a(),
                      "pos" -> pos(16, 16),
                      "value" -> "_"
                    )
                  ),
                  "pos" -> pos(16, 16)
                ),
                d(
                  "type" -> "Defn.Def",
                  "children" -> a(
                    d(
                      "type" -> "Term.Name",
                      "children" -> a(),
                      "pos" -> pos(20, 24),
                      "value" -> "main"
                    ),
                    d(
                      "type" -> "Term.Param",
                      "children" -> a(
                        d(
                          "type" -> "Term.Name",
                          "children" -> a(),
                          "pos" -> pos(25, 29),
                          "value" -> "args"
                        ),
                        d(
                          "type" -> "Type.Apply",
                          "children" -> a(
                            d(
                              "type" -> "Type.Name",
                              "children" -> a(),
                              "pos" -> pos(31, 36),
                              "value" -> "Array"
                            ),
                            d(
                              "type" -> "Type.Name",
                              "children" -> a(),
                              "pos" -> pos(37, 43),
                              "value" -> "String"
                            )
                          ),
                          "pos" -> pos(31, 44)
                        )
                      ),
                      "pos" -> pos(25, 44)
                    ),
                    d(
                      "type" -> "Type.Name",
                      "children" -> a(),
                      "pos" -> pos(47, 51),
                      "value" -> "Unit"
                    ),
                    d(
                      "type" -> "Term.Apply",
                      "children" -> a(
                        d(
                          "type" -> "Term.Name",
                          "children" -> a(),
                          "pos" -> pos(58, 65),
                          "value" -> "println"
                        ),
                        d(
                          "type" -> "Lit.String",
                          "children" -> a(),
                          "pos" -> pos(66, 80),
                          "value" -> "Hello, World"
                        )
                      ),
                      "pos" -> pos(58, 81)
                    )
                  ),
                  "pos" -> pos(16, 81)
                )
              ),
              "pos" -> pos(12, 83)
            )
          ),
          "pos" -> pos(0, 83)
        )
      ),
      "pos" -> pos(0, 83)
    ).asInstanceOf[js.Dictionary[Any]]

    check(parsed, expected)
  }

}

