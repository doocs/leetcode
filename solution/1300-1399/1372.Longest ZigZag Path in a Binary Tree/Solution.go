/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func longestZigZag(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode, l, r int)
	dfs = func(root *TreeNode, l, r int) {
		if root == nil {
			return
		}
		ans = max(ans, max(l, r))
		dfs(root.Left, r+1, 0)
		dfs(root.Right, 0, l+1)
	}
	dfs(root, 0, 0)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}