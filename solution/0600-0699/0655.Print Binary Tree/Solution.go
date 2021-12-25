/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func printTree(root *TreeNode) [][]string {
	var height func(root *TreeNode) int
	height = func(root *TreeNode) int {
		if root == nil {
			return -1
		}
		return 1 + max(height(root.Left), height(root.Right))
	}
	h := height(root)
	m, n := h+1, (1<<(h+1))-1
	ans := make([][]string, m)
	for i := range ans {
		ans[i] = make([]string, n)
		for j := range ans[i] {
			ans[i][j] = ""
		}
	}
	var dfs func(root *TreeNode, r, c int)
	dfs = func(root *TreeNode, r, c int) {
		if root == nil {
			return
		}
		ans[r][c] = strconv.Itoa(root.Val)
		dfs(root.Left, r+1, c-int(math.Pow(float64(2), float64(h-r-1))))
		dfs(root.Right, r+1, c+int(math.Pow(float64(2), float64(h-r-1))))
	}

	dfs(root, 0, (n-1)/2)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}