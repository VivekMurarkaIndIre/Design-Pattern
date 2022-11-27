package Exercise6

import java.util

trait treeIterator:
  def hasChild(node: Node):Boolean
  def getChild(node: Node):String

class ConcreteInfixTreeIterator extends treeIterator:
  override def getChild(node: Node): String =
    var label:String= ""
    if(hasChild(node) && node.isInstanceOf[BinaryOperatorNode]==true)
      label="("
      label =label+ getChild(node.asInstanceOf[BinaryOperatorNode].leftNode)
      label=label+node.label
      label= label+getChild(node.asInstanceOf[BinaryOperatorNode].rightNode)
      label= label+")"
    else
      label= label+node.label

    label

  override def hasChild(node: Node): Boolean =
    if(node.isInstanceOf[LeafNode]==true)
      false
    else
      true

class ConcretePostfixTreeIterator extends treeIterator:
  override def getChild(node: Node): String =
    var label:String= ""
    if(node.isInstanceOf[BinaryOperatorNode]==true)
      label =label+ getChild(node.asInstanceOf[BinaryOperatorNode].leftNode)
      label= label+getChild(node.asInstanceOf[BinaryOperatorNode].rightNode)
      label=label+node.label
    else
      label= label+node.label

    label

  override def hasChild(node: Node): Boolean =
    if(node.isInstanceOf[BinaryOperatorNode]==true)
      true
    else
      false


trait Visitor:
  def traverse(node:BinaryOperatorNode): String
  def assignleaf(node:LeafNode):String =
    node.label

class ConcreteInfixVisitor extends Visitor:
  override def traverse(node: BinaryOperatorNode): String=
    val iterator= new ConcreteInfixTreeIterator()
    iterator.getChild(node)


class ConcretePostfixVisitor extends Visitor:
  override def traverse(node: BinaryOperatorNode): String=
    val iterator= new ConcretePostfixTreeIterator()
    iterator.getChild(node)



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

  //println(AdditionNode(MultiplicationNode(AdditionNode(LeafNode("a"), LeafNode("b"),visitor), LeafNode("c"),visitor),LeafNode("7"),visitor).traverse)
  println("Postfix Traversal:")
  visitor = new ConcretePostfixVisitor()
  list.forEach(_.accept(visitor))
  println(node3.traverse)

