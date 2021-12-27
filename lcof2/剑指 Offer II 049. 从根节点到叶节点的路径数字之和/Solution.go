/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumNumbers(root *TreeNode) int {
	var dfs func(root *TreeNode, presum int) int
	dfs = func(root *TreeNode, presum int) int {
		if root == nil {
			return 0
		}
		presum = presum*10 + root.Val
		if root.Left == nil && root.Right == nil {
			return presum
		}
		return dfs(root.Left, presum) + dfs(root.Right, presum)
	}
	return dfs(root, 0)
}