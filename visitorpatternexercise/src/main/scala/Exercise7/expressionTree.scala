package Exercise7

import java.util
trait Visitor:
  def node:Node
case class ConcreteBinaryInfixVisitor(node: Node) extends Visitor:
  def traverse(): String =
    var infix: String=""
    if(node.isInstanceOf[BinaryOperatorNode])
      infix =  "(" + node.asInstanceOf[BinaryOperatorNode].leftNode.traverse + " " + node.label + " " + node.asInstanceOf[BinaryOperatorNode].rightNode.traverse + ")"
    else
      infix = node.label
    infix.toString


case class ConcreteBinaryPostfixVisitor(node: Node) extends Visitor:
  def traverse(): String =
    if (node.isInstanceOf[BinaryOperatorNode])
      node.asInstanceOf[BinaryOperatorNode].leftNode.traverse + " " + node.asInstanceOf[BinaryOperatorNode].rightNode.traverse + " " + node.label
    else
      node.label

//Cre
abstract class Node:
  def label: String
  var traverse: String=null

  def accept(visitor: Visitor): Unit =
    traverse = visitor match
      case infix: ConcreteBinaryInfixVisitor => infix.traverse()
      case postfix: ConcreteBinaryPostfixVisitor => postfix.traverse()


abstract class BinaryOperatorNode(left: Node, right: Node) extends Node:
  var leftNode: Node = left
  var rightNode: Node = right

class AdditionNode(left: Node, right: Node) extends BinaryOperatorNode(left, right):
  val label = "+"

class MultiplicationNode(left: Node, right: Node) extends BinaryOperatorNode(left, right):
  val label = "*"

class ModulusNode(left: Node, right: Node) extends BinaryOperatorNode(left, right):
  val label = "%"

class LeafNode(override val label: String) extends Node:
  traverse = label
  
@main def main(): Unit =


  var node1 = AdditionNode(LeafNode("a"), LeafNode("b"))
  var node2 = MultiplicationNode(node1, LeafNode("c"))
  var node3 = AdditionNode(node2, LeafNode("7"))

  var list = List(node1,node2,node3)

  println("Infix Traversal:")
  var visitor: Visitor=null

  for(n <- list)
    visitor = new ConcreteBinaryInfixVisitor(n)
    n.accept(visitor)

  println(node3.traverse)
  println("Postfix Traversal:")
  for (n <- list)
    visitor = new ConcreteBinaryPostfixVisitor(n)
    n.accept(visitor)
  println(node3.traverse)



