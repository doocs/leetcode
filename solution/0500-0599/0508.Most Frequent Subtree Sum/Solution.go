/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findFrequentTreeSum(root *TreeNode) []int {
	counter := make(map[int]int)
	mx := 0
	var dfs func(root *TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		s := root.Val + dfs(root.Left) + dfs(root.Right)
		counter[s]++
		if mx < counter[s] {
			mx = counter[s]
		}
		return s
	}
	dfs(root)
	var ans []int
	for k, v := range counter {
		if v == mx {
			ans = append(ans, k)
		}
	}
	return ans
}