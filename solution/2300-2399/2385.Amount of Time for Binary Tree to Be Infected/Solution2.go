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

	var dfs2 func(int, int) int
	dfs2 = func(i, fa int) int {
		ans := 0
		for _, j := range g[i] {
			if j != fa {
				ans = max(ans, 1+dfs2(j, i))
			}
		}
		return ans
	}

	dfs(root)
	return dfs2(start, -1)
}