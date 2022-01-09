/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findClosestLeaf(root *TreeNode, k int) int {
	g := make(map[*TreeNode][]*TreeNode)
	var dfs func(root, p *TreeNode)
	dfs = func(root, p *TreeNode) {
		if root == nil {
			return
		}
		g[root] = append(g[root], p)
		g[p] = append(g[p], root)
		dfs(root.Left, root)
		dfs(root.Right, root)
	}
	dfs(root, nil)
	var q []*TreeNode
	for t, _ := range g {
		if t != nil && t.Val == k {
			q = append(q, t)
			break
		}
	}
	seen := make(map[*TreeNode]bool)
	for len(q) > 0 {
		node := q[0]
		q = q[1:]
		seen[node] = true
		if node != nil {
			if node.Left == nil && node.Right == nil {
				return node.Val
			}
			for _, next := range g[node] {
				if !seen[next] {
					q = append(q, next)
				}
			}
		}
	}
	return 0
}