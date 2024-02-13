/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func verticalTraversal(root *TreeNode) (ans [][]int) {
	nodes := [][3]int{}
	var dfs func(*TreeNode, int, int)
	dfs = func(root *TreeNode, i, j int) {
		if root == nil {
			return
		}
		nodes = append(nodes, [3]int{j, i, root.Val})
		dfs(root.Left, i+1, j-1)
		dfs(root.Right, i+1, j+1)
	}
	dfs(root, 0, 0)
	sort.Slice(nodes, func(i, j int) bool {
		a, b := nodes[i], nodes[j]
		return a[0] < b[0] || a[0] == b[0] && (a[1] < b[1] || a[1] == b[1] && a[2] < b[2])
	})
	prev := -2000
	for _, node := range nodes {
		j, val := node[0], node[2]
		if j != prev {
			ans = append(ans, nil)
			prev = j
		}
		ans[len(ans)-1] = append(ans[len(ans)-1], val)
	}
	return
}