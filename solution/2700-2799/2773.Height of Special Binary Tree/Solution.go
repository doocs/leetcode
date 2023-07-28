/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func heightOfTree(root *TreeNode) (ans int) {
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, d int) {
		if ans < d {
			ans = d
		}
		d++
		if root.Left != nil && root.Left.Right != root {
			dfs(root.Left, d)
		}
		if root.Right != nil && root.Right.Left != root {
			dfs(root.Right, d)
		}
	}
	dfs(root, 0)
	return
}