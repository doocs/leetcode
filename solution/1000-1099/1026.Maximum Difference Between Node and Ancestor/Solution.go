/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxAncestorDiff(root *TreeNode) (ans int) {
	var dfs func(*TreeNode, int, int)
	dfs = func(root *TreeNode, mi, mx int) {
		if root == nil {
			return
		}
		ans = max(ans, max(abs(mi-root.Val), abs(mx-root.Val)))
		mi = min(mi, root.Val)
		mx = max(mx, root.Val)
		dfs(root.Left, mi, mx)
		dfs(root.Right, mi, mx)
	}
	dfs(root, root.Val, root.Val)
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}