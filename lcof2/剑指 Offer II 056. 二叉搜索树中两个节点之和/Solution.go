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

	var find func(root *TreeNode, k int) bool
	find = func(root *TreeNode, k int) bool {
		if root == nil {
			return false
		}
		if nodes[k-root.Val] {
			return true
		}
		nodes[root.Val] = true
		return find(root.Left, k) || find(root.Right, k)
	}
	return find(root, k)
}