/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 *     Next *Node
 * }
 */

func connect(root *Node) *Node {
    if root == nil {
        return nil
    }
    if root.Left != nil && root.Right != nil {
        root.Left.Next = root.Right
    }
    if root.Left != nil && root.Right == nil {
        root.Left.Next = getNext(root.Next)
    }
    if root.Right != nil {
        root.Right.Next = getNext(root.Next)
    }
    //先连接右侧节点
    connect(root.Right)
    connect(root.Left)
    return root
}

func getNext(node *Node) *Node {
    for node != nil {
        if node.Left != nil {
            return node.Left
        }
        if node.Right != nil {
            return node.Right
        }
        node = node.Next
    }
    return nil
}
