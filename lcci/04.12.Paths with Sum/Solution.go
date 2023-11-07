/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func pathSum(root *TreeNode, sum int) int {
	cnt := map[int]int{0: 1}
	var dfs func(*TreeNode, int) int
	dfs = func(root *TreeNode, s int) int {
		if root == nil {
			return 0
		}
		s += root.Val
		ans := cnt[s-sum]
		cnt[s]++
		ans += dfs(root.Left, s)
		ans += dfs(root.Right, s)
		cnt[s]--
		return ans
	}
	return dfs(root, 0)
}