/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumNumbers(root *TreeNode) int {
	return dfs(root, 0)
}

func dfs(root *TreeNode, presum int) int {
	if root == nil {
		return 0
	}
	s := presum*10 + root.Val
	if root.Left == nil && root.Right == nil {
		return s
	}
	return dfs(root.Left, s) + dfs(root.Right, s)
}