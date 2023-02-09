/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isCousins(root *TreeNode, x int, y int) bool {
	var p1, p2 *TreeNode
	var d1, d2 int
	var dfs func(*TreeNode, *TreeNode, int)
	dfs = func(root *TreeNode, fa *TreeNode, d int) {
		if root == nil {
			return
		}
		if root.Val == x {
			p1, d1 = fa, d
		}
		if root.Val == y {
			p2, d2 = fa, d
		}
		dfs(root.Left, root, d+1)
		dfs(root.Right, root, d+1)
	}
	dfs(root, nil, 0)
	return p1 != p2 && d1 == d2
}