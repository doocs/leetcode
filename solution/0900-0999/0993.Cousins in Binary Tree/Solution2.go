/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isCousins(root *TreeNode, x int, y int) bool {
	var d1, d2 int
	var p1, p2 *TreeNode
	var dfs func(root, parent *TreeNode, depth int)
	dfs = func(root, parent *TreeNode, depth int) {
		if root == nil {
			return
		}
		if root.Val == x {
			d1, p1 = depth, parent
		} else if root.Val == y {
			d2, p2 = depth, parent
		}
		dfs(root.Left, root, depth+1)
		dfs(root.Right, root, depth+1)
	}
	dfs(root, nil, 0)
	return d1 == d2 && p1 != p2
}