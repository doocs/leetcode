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
		return nil
	}
	var head, pre *Node
	var dfs func(*Node)
	dfs = func(root *Node) {
		if root == nil {
			return
		}
		dfs(root.Left)
		if pre != nil {
			pre.Right = root
		} else {
			head = root
		}
		root.Left = pre
		pre = root
		dfs(root.Right)
	}
	dfs(root)
	head.Left = pre
	pre.Right = head
	return head
}