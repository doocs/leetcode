/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findLeaves(root *TreeNode) (ans [][]int) {
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		h := max(l, r)
		if len(ans) == h {
			ans = append(ans, []int{})
		}
		ans[h] = append(ans[h], root.Val)
		return h + 1
	}
	dfs(root)
	return
}