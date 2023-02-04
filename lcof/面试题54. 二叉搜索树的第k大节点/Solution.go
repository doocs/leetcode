/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func kthLargest(root *TreeNode, k int) (ans int) {
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil || k == 0 {
			return
		}
		dfs(root.Right)
		k--
		if k == 0 {
			ans = root.Val
		}
		dfs(root.Left)
	}
	dfs(root)
	return
}