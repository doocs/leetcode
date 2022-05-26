/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func longestConsecutive(root *TreeNode) int {
	ans := 1
	var dfs func(root, p *TreeNode, t int)
	dfs = func(root, p *TreeNode, t int) {
		if root == nil {
			return
		}
		if p != nil && p.Val+1 == root.Val {
			t++
			ans = max(ans, t)
		} else {
			t = 1
		}
		dfs(root.Left, root, t)
		dfs(root.Right, root, t)
	}
	dfs(root, nil, 1)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}