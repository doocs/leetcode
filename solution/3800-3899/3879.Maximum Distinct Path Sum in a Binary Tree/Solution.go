/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxSum(root *TreeNode) int {
	g := map[*TreeNode][]*TreeNode{}

	var dfs func(node, p *TreeNode)
	dfs = func(node, p *TreeNode) {
		if node == nil {
			return
		}
		g[node] = append(g[node], p, node.Left, node.Right)
		dfs(node.Left, node)
		dfs(node.Right, node)
	}

	vis := map[int]bool{}

	var dfs2 func(node *TreeNode) int
	dfs2 = func(node *TreeNode) int {
		if node == nil || vis[node.Val] {
			return 0
		}
		vis[node.Val] = true
		res := node.Val
		best := 0
		for _, nxt := range g[node] {
			if v := dfs2(nxt); v > best {
				best = v
			}
		}
		vis[node.Val] = false
		return res + best
	}

	dfs(root, nil)

	ans := math.MinInt
	for node := range g {
		ans = max(ans, dfs2(node))
		clear(vis)
	}
	return ans
}
