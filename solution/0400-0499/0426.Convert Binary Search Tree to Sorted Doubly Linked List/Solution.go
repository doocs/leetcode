/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Left *Node
 *     Right *Node
 * }
 */

func treeToDoublyList(root *Node) *Node {
	if root == nil {
		return root
	}
	var prev, head *Node

	var dfs func(root *Node)
	dfs = func(root *Node) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if prev != nil {
			prev.Right = root
			root.Left = prev
		} else {
			head = root
		}
		prev = root
		dfs(root.Right)
	}
	dfs(root)
	prev.Right = head
	head.Left = prev
	return head
}