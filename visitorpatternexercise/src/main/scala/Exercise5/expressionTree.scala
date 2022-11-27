package Exercise5

import Exercise4.BinaryOperatorNode


trait Visitor:
  var nodeCount:Int=0
  def count(node:BinaryOperatorNode): Unit

class ConcreteCountVisitor extends Visitor:
  override def count(node: BinaryOperatorNode): Unit=
    if(node.isInstanceOf[AdditionNode] == true)
      nodeCount+=1


abstract class Node:
  def label: String
  def infix: String


abstract class BinaryOperatorNode(left: Node, right: Node) extends Node:
  def accept(visitor: Visitor):Unit
  def infix: String = "(" + left.infix + " " + label + " " + right.infix + ")"

class AdditionNode(left: Node, right: Node) extends BinaryOperatorNode(left, right):
  val label = "+"
  override def accept(visitor: Visitor): Unit =
    visitor.count(this)

class MultiplicationNode(left: Node, right: Node) extends BinaryOperatorNode(left, right):
  val label = "*"
  override def accept(visitor: Visitor): Unit =
    visitor.count(this)

class LeafNode(override val label: String) extends Node:
  def infix = label

@main def main(): Unit =

  println("Infix Traversal:")
  var visitor: Visitor = new ConcreteCountVisitor()
  var node1 = AdditionNode(LeafNode("a"), LeafNode("b"))
  node1.accept(visitor)
  var node2 = MultiplicationNode(node1, LeafNode("c"))
  node2.accept(visitor)
  var node3 = AdditionNode(node2, LeafNode("7"))
  node3.accept(visitor)
  println(node3.infix)
  println("Number of Additional Nodes:  "+visitor.nodeCount)