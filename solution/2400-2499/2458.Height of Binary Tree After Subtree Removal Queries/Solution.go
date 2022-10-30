/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func treeQueries(root *TreeNode, queries []int) (ans []int) {
	d := map[*TreeNode]int{}
	var f func(*TreeNode) int
	f = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := f(root.Left), f(root.Right)
		d[root] = 1 + max(l, r)
		return d[root]
	}
	f(root)
	res := make([]int, len(d)+1)
	var dfs func(*TreeNode, int, int)
	dfs = func(root *TreeNode, depth, rest int) {
		if root == nil {
			return
		}
		depth++
		res[root.Val] = rest
		dfs(root.Left, depth, max(rest, depth+d[root.Right]))
		dfs(root.Right, depth, max(rest, depth+d[root.Left]))
	}
	dfs(root, -1, 0)
	for _, v := range queries {
		ans = append(ans, res[v])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}