/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func generateTrees(n int) []*TreeNode {
	var dfs func(int, int) []*TreeNode
	dfs = func(i, j int) []*TreeNode {
		if i > j {
			return []*TreeNode{nil}
		}
		ans := []*TreeNode{}
		for v := i; v <= j; v++ {
			left := dfs(i, v-1)
			right := dfs(v+1, j)
			for _, l := range left {
				for _, r := range right {
					ans = append(ans, &TreeNode{v, l, r})
				}
			}
		}
		return ans
	}
	return dfs(1, n)
}