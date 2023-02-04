/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func evaluateTree(root *TreeNode) bool {
	if root.Left == nil {
		return root.Val == 1
	}
	l, r := evaluateTree(root.Left), evaluateTree(root.Right)
	if root.Val == 2 {
		return l || r
	}
	return l && r
}