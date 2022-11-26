package Exercise1



trait Visitor:
  def traverseInfix(node:BinaryOperatorNode): String

class ConcreteInfixVisitor extends Visitor:
  override def traverseInfix(node: BinaryOperatorNode): String=
    val infix: String =  "(" + node.leftNode.traverse + " " + node.label + " " + node.rightNode.traverse + ")"
    infix.toString


abstract class Node:
  def label: String
  def traverse: String

abstract class BinaryOperatorNode(left: Node, right: Node,visitor:Visitor  ) extends Node:
  var leftNode: Node = left
  var rightNode: Node = right
  def traverse = visitor.traverseInfix(this)


class AdditionNode(left: Node, right: Node,visitor:Visitor ) extends BinaryOperatorNode(left, right,visitor):
  val label = "+"


class MultiplicationNode(left: Node, right: Node,visitor:Visitor ) extends BinaryOperatorNode(left, right,visitor):
  val label = "*"


class LeafNode(override val label: String) extends Node:
  def traverse = label

@main def main(): Unit =

  var visitor = new ConcreteInfixVisitor()
  println(AdditionNode(MultiplicationNode(AdditionNode(LeafNode("a"), LeafNode("b"),visitor), LeafNode("c"),visitor),LeafNode("7"),visitor).traverse)


