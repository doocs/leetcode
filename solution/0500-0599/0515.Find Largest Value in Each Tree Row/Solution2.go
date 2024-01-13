/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func largestValues(root *TreeNode) []int {
	var ans []int
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, curr int) {
		if root == nil {
			return
		}
		if curr == len(ans) {
			ans = append(ans, root.Val)
		} else {
			ans[curr] = max(ans[curr], root.Val)
		}
		dfs(root.Left, curr+1)
		dfs(root.Right, curr+1)
	}
	dfs(root, 0)
	return ans
}