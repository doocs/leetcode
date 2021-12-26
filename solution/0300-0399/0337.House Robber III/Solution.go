/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func rob(root *TreeNode) int {
	memo := make(map[*TreeNode]int)
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		if _, ok := memo[root]; ok {
			return memo[root]
		}
		a := dfs(root.Left) + dfs(root.Right)
		b := root.Val
		if root.Left != nil {
			b += dfs(root.Left.Left) + dfs(root.Left.Right)
		}
		if root.Right != nil {
			b += dfs(root.Right.Left) + dfs(root.Right.Right)
		}
		res := max(a, b)
		memo[root] = res
		return res
	}
	return dfs(root)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}