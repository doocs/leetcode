/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func levelMedian(root *TreeNode, level int) int {
	nums := make([]int, 0)

	var dfs func(*TreeNode, int)
	dfs = func(node *TreeNode, i int) {
		if node == nil {
			return
		}
		dfs(node.Left, i+1)
		if i == level {
			nums = append(nums, node.Val)
		}
		dfs(node.Right, i+1)
	}

	dfs(root, 0)
	if len(nums) == 0 {
		return -1
	}
	return nums[len(nums)/2]
}
