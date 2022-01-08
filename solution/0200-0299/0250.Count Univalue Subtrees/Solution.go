/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func countUnivalSubtrees(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode) bool
	dfs = func(root *TreeNode) bool {
		if root == nil {
			return true
		}
		left, right := dfs(root.Left), dfs(root.Right)
		t := true
		if root.Left != nil && root.Left.Val != root.Val {
			t = false
		}
		if root.Right != nil && root.Right.Val != root.Val {
			t = false
		}
		if left && t && right {
			ans++
		}
		return left && t && right
	}
	dfs(root)
	return ans
}