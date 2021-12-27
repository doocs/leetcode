/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findDistance(root *TreeNode, p int, q int) int {
	var lca func(root *TreeNode, p int, q int) *TreeNode
	lca = func(root *TreeNode, p int, q int) *TreeNode {
		if root == nil || root.Val == p || root.Val == q {
			return root
		}
		left, right := lca(root.Left, p, q), lca(root.Right, p, q)
		if left == nil {
			return right
		}
		if right == nil {
			return left
		}
		return root
	}
	var dfs func(root *TreeNode, v int) int
	dfs = func(root *TreeNode, v int) int {
		if root == nil {
			return -1
		}
		if root.Val == v {
			return 0
		}
		left, right := dfs(root.Left, v), dfs(root.Right, v)
		if left == -1 && right == -1 {
			return -1
		}
		return 1 + max(left, right)
	}
	g := lca(root, p, q)
	return dfs(g, p) + dfs(g, q)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}