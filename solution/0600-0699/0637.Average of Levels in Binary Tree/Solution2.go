/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func averageOfLevels(root *TreeNode) []float64 {
	s := []int{}
	cnt := []int{}
	var dfs func(root *TreeNode, i int)
	dfs = func(root *TreeNode, i int) {
		if root == nil {
			return
		}
		if len(s) == i {
			s = append(s, root.Val)
			cnt = append(cnt, 1)
		} else {
			s[i] += root.Val
			cnt[i]++
		}
		dfs(root.Left, i+1)
		dfs(root.Right, i+1)
	}
	dfs(root, 0)
	ans := []float64{}
	for i, t := range s {
		ans = append(ans, float64(t)/float64(cnt[i]))
	}
	return ans
}