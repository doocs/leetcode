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
	var dfs func(*Node, *Node)
	dfs = func(left, right *Node) {
		if left == nil || right == nil {
			return
		}
		left.Next = right
		dfs(left.Left, left.Right)
		dfs(left.Right, right.Left)
		dfs(right.Left, right.Right)
	}
	if root != nil {
		dfs(root.Left, root.Right)
	}
	return root
}