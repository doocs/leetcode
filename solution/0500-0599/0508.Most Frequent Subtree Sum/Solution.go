/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findFrequentTreeSum(root *TreeNode) (ans []int) {
	cnt := map[int]int{}
	var mx int
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		s := root.Val + dfs(root.Left) + dfs(root.Right)
		cnt[s]++
		mx = max(mx, cnt[s])
		return s
	}
	dfs(root)
	for k, v := range cnt {
		if v == mx {
			ans = append(ans, k)
		}
	}
	return
}
