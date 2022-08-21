/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func amountOfTime(root *TreeNode, start int) int {
	g := map[int][]int{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		if root.Left != nil {
			g[root.Val] = append(g[root.Val], root.Left.Val)
			g[root.Left.Val] = append(g[root.Left.Val], root.Val)
		}
		if root.Right != nil {
			g[root.Val] = append(g[root.Val], root.Right.Val)
			g[root.Right.Val] = append(g[root.Right.Val], root.Val)
		}
		dfs(root.Left)
		dfs(root.Right)
	}

	dfs(root)
	q := []int{start}
	ans := -1
	vis := map[int]bool{}
	for len(q) > 0 {
		ans++
		for n := len(q); n > 0; n-- {
			i := q[0]
			q = q[1:]
			vis[i] = true
			for _, j := range g[i] {
				if !vis[j] {
					q = append(q, j)
				}
			}
		}
	}
	return ans
}