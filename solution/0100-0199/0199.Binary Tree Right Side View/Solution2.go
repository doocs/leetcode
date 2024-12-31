/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func rightSideView(root *TreeNode) (ans []int) {
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, depth int) {
		if root == nil {
			return
		}
		if len(ans) == depth {
			ans = append(ans, root.Val)
		}
		dfs(root.Right, depth+1)
		dfs(root.Left, depth+1)
	}
	dfs(root, 0)
	return
}
