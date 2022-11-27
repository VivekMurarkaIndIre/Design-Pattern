package Exercise4
import java.util
trait Visitor:
  def traverse(node: BinaryOperatorNode): String
  def assignleaf(node: LeafNode): String =
    node.label

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
  var traverse: String=null
  def accept(visitor: Visitor):Unit

abstract class BinaryOperatorNode(left: Node, right: Node) extends Node:
  var leftNode: Node = left
  var rightNode: Node = right




class AdditionNode(left: Node, right: Node) extends BinaryOperatorNode(left, right):
  val label = "+"
  override def accept(visitor: Visitor): Unit =
    traverse = visitor.traverse(this)

class MultiplicationNode(left: Node, right: Node) extends BinaryOperatorNode(left, right):
  val label = "*"
  override def accept(visitor: Visitor): Unit =
    traverse = visitor.traverse(this)

class ModulusNode(left: Node, right: Node) extends BinaryOperatorNode(left, right):
  val label = "%"

  override def accept(visitor: Visitor): Unit =
    traverse = visitor.traverse(this)

class LeafNode(override val label: String) extends Node:
  traverse = label

  override def accept(visitor: Visitor): Unit =
    traverse = visitor.assignleaf(this)




@main def main(): Unit =


  var node1 = AdditionNode(LeafNode("a"), LeafNode("b"))
  var node2 = MultiplicationNode(node1, LeafNode("c"))
  var node3 = AdditionNode(node2, LeafNode("7"))

  var list = new util.ArrayList[Node]()
  list.add(node1)
  list.add(node2)
  list.add(node3)
  println("Infix Traversal:")
  var visitor: Visitor = new ConcreteInfixVisitor()
  list.forEach(_.accept(visitor))
  println(node3.traverse)
  println("Postfix Traversal:")
  visitor = new ConcretePostfixVisitor()

  list.forEach(_.accept(visitor))

  println(node3.traverse)

  val a = "5"
  val b = "6"
  val c = "10"
  println("Infix Traversal Evaluation:")
  node1 = AdditionNode(LeafNode(a), LeafNode(b))
  node2 = MultiplicationNode(node1, LeafNode(c))
  node3 = AdditionNode(node2, LeafNode("7"))

  list = new util.ArrayList[Node]()
  list.add(node1)
  list.add(node2)
  list.add(node3)

  visitor = new ConcreteVisitInfixTreeEvaluation()
  list.forEach(_.accept(visitor))

  println(node3.traverse)


  println("Infix Traversal witH Modulus:")
  node1 = AdditionNode(LeafNode("a"), LeafNode("b"))
  node2 = MultiplicationNode(node1, LeafNode("c"))
  var node4= ModulusNode(LeafNode("13"),LeafNode("6"))
  node3 = AdditionNode(node2, node4)

  list = new util.ArrayList[Node]()
  list.add(node1)
  list.add(node2)
  list.add(node4)
  list.add(node3)
  visitor = new ConcreteInfixVisitor()
  list.forEach(_.accept(visitor))

  println(node3.traverse)


  println("Infix Traversal Evaluation with Modulus:")
  node1 = AdditionNode(LeafNode(a), LeafNode(b))
  node2 = MultiplicationNode(node1, LeafNode(c))
  node4= ModulusNode(LeafNode("13"),LeafNode("6"))
  node3 = AdditionNode(node2, node4)
  list = new util.ArrayList[Node]()
  list.add(node1)
  list.add(node2)
  list.add(node4)
  list.add(node3)
  visitor = new ConcreteVisitInfixTreeEvaluation()
  list.forEach(_.accept(visitor))

  println(node3.traverse)
