/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func allPossibleFBT(n int) []*TreeNode {
	f := map[int][]*TreeNode{}
	var dfs func(n int) []*TreeNode
	dfs = func(n int) []*TreeNode {
		if v, ok := f[n]; ok {
			return v
		}
		if n == 1 {
			return []*TreeNode{&TreeNode{Val: 0}}
		}
		res := []*TreeNode{}
		for i := 0; i < n-1; i++ {
			j := n - i - 1
			for _, left := range dfs(i) {
				for _, right := range dfs(j) {
					res = append(res, &TreeNode{0, left, right})
				}
			}
		}
		f[n] = res
		return res
	}
	return dfs(n)
}