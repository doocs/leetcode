/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sortedArrayToBST(nums []int) *TreeNode {
	var dfs func(i, j int) *TreeNode
	dfs = func(i, j int) *TreeNode {
		if i > j {
			return nil
		}
		if i == j {
			return &TreeNode{Val: nums[i]}
		}
		mid := (i + j) >> 1
		return &TreeNode{Val: nums[mid], Left: dfs(i, mid-1), Right: dfs(mid+1, j)}
	}

	return dfs(0, len(nums)-1)
}