package Exercise4

trait Visitor:
  def traverse(node:BinaryOperatorNode): String

class ConcreteInfixVisitor extends Visitor:
  override def traverse(node: BinaryOperatorNode): String=
    val infix: String =  "(" + node.leftNode.traverse + " " + node.label + " " + node.rightNode.traverse + ")"
    infix.toString

class ConcretePostfixVisitor extends Visitor:
  override def traverse(node: BinaryOperatorNode): String=
    val postfix: String =   node.leftNode.traverse + " "  + node.rightNode.traverse + " " + node.label
    postfix.toString



class ConcreteVisitInfixTreeEvaluation extends Visitor:
  override def traverse(node: BinaryOperatorNode): String=
    var result: String=""
    if(node.label=="+")
      result= ((node.leftNode.traverse).toInt + (node.rightNode.traverse).toInt).toString
    else if(node.label=="*")
      result = ((node.leftNode.traverse).toInt * ( node.rightNode.traverse).toInt).toString
    else if (node.label == "%")
      result = ((node.leftNode.traverse).toInt % (node.rightNode.traverse).toInt).toString
    else
      result = node.label

    result

abstract class Node:
  def label: String
  def traverse: String

abstract class BinaryOperatorNode(left: Node, right: Node,visitor:Visitor  ) extends Node:
  var leftNode: Node = left
  var rightNode: Node = right
  def traverse = visitor.traverse(this)


class AdditionNode(left: Node, right: Node,visitor:Visitor ) extends BinaryOperatorNode(left, right,visitor):
  val label = "+"

class MultiplicationNode(left: Node, right: Node,visitor:Visitor ) extends BinaryOperatorNode(left, right,visitor):
  val label = "*"

class ModulusNode(left: Node, right: Node,visitor:Visitor ) extends BinaryOperatorNode(left, right,visitor):
  val label = "%"


class LeafNode(override val label: String) extends Node:
  def traverse = label

@main def main(): Unit =


  println("Infix Traversal:")
  var visitor: Visitor = new ConcreteInfixVisitor()

  println(AdditionNode(MultiplicationNode(AdditionNode(LeafNode("a"), LeafNode("b"), visitor), LeafNode("c"), visitor), LeafNode("7"), visitor).traverse)
  println("Postfix Traversal:")
  visitor = new ConcretePostfixVisitor()
  println(AdditionNode(MultiplicationNode(AdditionNode(LeafNode("a"), LeafNode("b"), visitor), LeafNode("c"), visitor), LeafNode("7"), visitor).traverse)


  var a = "5"
  var b="6"
  var c="10"
  println("Infix Traversal Evaluation:")
  visitor = new ConcreteVisitInfixTreeEvaluation()
  println(AdditionNode(MultiplicationNode(AdditionNode(LeafNode(a), LeafNode(b),visitor), LeafNode(c),visitor),LeafNode("7"),visitor).traverse)


  println("Infix Traversal witH Modulus:")
  visitor = new ConcreteInfixVisitor()

  println(AdditionNode(MultiplicationNode(AdditionNode(LeafNode("a"), LeafNode("b"), visitor), LeafNode("c"), visitor), ModulusNode (LeafNode("13"),LeafNode("6"),visitor), visitor).traverse)

  println("Infix Traversal Evaluation with Modulus:")
  visitor = new ConcreteVisitInfixTreeEvaluation()
  println(AdditionNode(MultiplicationNode(AdditionNode(LeafNode(a), LeafNode(b), visitor), LeafNode(c), visitor), ModulusNode (LeafNode("13"),LeafNode("6"),visitor), visitor).traverse)

