package Exercise1

import java.util


trait Visitor:
  def traverse(node:BinaryOperatorNode): String

  def assignleaf(node: LeafNode): String =
    node.label

class ConcreteInfixVisitor extends Visitor:
  override def traverse(node: BinaryOperatorNode): String=
    val infix: String =  "(" + node.leftNode.traverse + " " + node.label + " " + node.rightNode.traverse + ")"
    infix.toString


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


class LeafNode(override val label: String) extends Node:
  traverse = label

  override def accept(visitor: Visitor): Unit =
    traverse = visitor.assignleaf(this)

@main def main(): Unit =

  //crete b-tree
  var node1 = AdditionNode(LeafNode("a"), LeafNode("b"))
  var node2 = MultiplicationNode(node1, LeafNode("c"))
  var node3 = AdditionNode(node2, LeafNode("7"))

  var list= new util.ArrayList[Node]()
  list.add(node1)
  list.add(node2)
  list.add(node3)
  println("Infix Traversal:")
  var visitor:Visitor = new ConcreteInfixVisitor()
  list.forEach(_.accept(visitor))

  println(node3.traverse)

