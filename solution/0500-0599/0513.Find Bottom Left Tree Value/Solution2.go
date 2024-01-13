/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findBottomLeftValue(root *TreeNode) int {
	ans, mx := 0, 0
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, curr int) {
		if root == nil {
			return
		}
		dfs(root.Left, curr+1)
		dfs(root.Right, curr+1)
		if mx < curr {
			mx = curr
			ans = root.Val
		}
	}
	dfs(root, 1)
	return ans
}