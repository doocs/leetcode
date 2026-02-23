/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumRootToLeaf(root *TreeNode) int {
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, x int) int {
		if root == nil {
			return 0
		}
		x = x<<1 | root.Val
		if root.Left == root.Right {
			return x
		}
		return dfs(root.Left, x) + dfs(root.Right, x)
	}
	return dfs(root, 0)
}
