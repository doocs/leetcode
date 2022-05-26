/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func goodNodes(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode, mx int)
	dfs = func(root *TreeNode, mx int) {
		if root == nil {
			return
		}
		if mx <= root.Val {
			ans++
			mx = root.Val
		}
		dfs(root.Left, mx)
		dfs(root.Right, mx)
	}
	dfs(root, -10000)
	return ans
}