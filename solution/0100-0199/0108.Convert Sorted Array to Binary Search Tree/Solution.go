/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sortedArrayToBST(nums []int) *TreeNode {
	return buildBST(nums, 0, len(nums)-1)
}

func buildBST(nums []int, start, end int) *TreeNode {
	if start > end {
		return nil
	}
	mid := (start + end) >> 1
	return &TreeNode{
		Val:   nums[mid],
		Left:  buildBST(nums, start, mid-1),
		Right: buildBST(nums, mid+1, end),
	}
}