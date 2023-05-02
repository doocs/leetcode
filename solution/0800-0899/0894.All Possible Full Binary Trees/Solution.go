/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func allPossibleFBT(n int) []*TreeNode {
	f := make([][]*TreeNode, n+1)
	var dfs func(int) []*TreeNode
	dfs = func(n int) []*TreeNode {
		if len(f[n]) > 0 {
			return f[n]
		}
		if n == 1 {
			return []*TreeNode{&TreeNode{Val: 0}}
		}
		ans := []*TreeNode{}
		for i := 0; i < n-1; i++ {
			j := n - 1 - i
			for _, left := range dfs(i) {
				for _, right := range dfs(j) {
					ans = append(ans, &TreeNode{0, left, right})
				}
			}
		}
		f[n] = ans
		return ans
	}
	return dfs(n)
}