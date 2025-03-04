func distanceK(root *TreeNode, target *TreeNode, k int) []int {
	g := make(map[*TreeNode]*TreeNode)
	ans := []int{}

	var dfs func(node, fa *TreeNode)
	dfs = func(node, fa *TreeNode) {
		if node == nil {
			return
		}
		g[node] = fa
		dfs(node.Left, node)
		dfs(node.Right, node)
	}

	var dfs2 func(node, fa *TreeNode, k int)
	dfs2 = func(node, fa *TreeNode, k int) {
		if node == nil {
			return
		}
		if k == 0 {
			ans = append(ans, node.Val)
			return
		}
		for _, nxt := range []*TreeNode{node.Left, node.Right, g[node]} {
			if nxt != fa {
				dfs2(nxt, node, k-1)
			}
		}
	}

	dfs(root, nil)
	dfs2(target, nil, k)

	return ans
}
