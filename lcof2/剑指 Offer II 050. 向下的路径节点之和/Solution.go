/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func pathSum(root *TreeNode, targetSum int) int {
	preSum := make(map[int]int)
	preSum[0] = 1

	var dfs func(*TreeNode, int) int
	dfs = func(node *TreeNode, cur int) int {
		if node == nil {
			return 0
		}

		cur += node.Val
		ret := preSum[cur-targetSum]

		preSum[cur]++
		ret += dfs(node.Left, cur)
		ret += dfs(node.Right, cur)
		preSum[cur]--

		return ret
	}

	return dfs(root, 0)
}
