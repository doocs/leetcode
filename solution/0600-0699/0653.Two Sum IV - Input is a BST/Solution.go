/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findTarget(root *TreeNode, k int) bool {
	nodes := make(map[int]bool)
	var find func(node *TreeNode, k int) bool
	find = func(node *TreeNode, k int) bool {
		if node == nil {
			return false
		}
		if nodes[k-node.Val] {
			return true
		}
		nodes[node.Val] = true
		return find(node.Left, k) || find(node.Right, k)
	}
	return find(root, k)

}