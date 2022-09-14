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
		return root
	}
	traversal(root.Left, root.Right)
	return root
}

func traversal(left, right *Node) {
	if left == nil || right == nil {
		return
	}
	left.Next = right
	traversal(left.Left, left.Right)
	traversal(left.Right, right.Left)
	traversal(right.Left, right.Right)
}