/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumRootToLeaf(root *TreeNode) int {
	var dfs func(root *TreeNode, t int) int
	dfs = func(root *TreeNode, t int) int {
		if root == nil {
			return 0
		}
		t = (t << 1) | root.Val
		if root.Left == nil && root.Right == nil {
			return t
		}
		return dfs(root.Left, t) + dfs(root.Right, t)
	}

	return dfs(root, 0)
}