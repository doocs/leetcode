/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxAncestorDiff(root *TreeNode) int {
	ans := 0
	var dfs func(root *TreeNode, mx, mi int)
	dfs = func(root *TreeNode, mx, mi int) {
		if root == nil {
			return
		}
		t := max(abs(root.Val-mx), abs(root.Val-mi))
		ans = max(ans, t)
		mx = max(mx, root.Val)
		mi = min(mi, root.Val)
		dfs(root.Left, mx, mi)
		dfs(root.Right, mx, mi)
	}
	dfs(root, root.Val, root.Val)
	return ans
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
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