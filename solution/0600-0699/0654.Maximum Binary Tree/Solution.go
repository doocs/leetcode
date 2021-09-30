/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructMaximumBinaryTree(nums []int) *TreeNode {
	return construct(nums, 0, len(nums)-1)
}

func construct(nums []int, l, r int) *TreeNode {
	if l > r {
		return nil
	}
	mx := l
	for i := l + 1; i <= r; i++ {
		if nums[mx] < nums[i] {
			mx = i
		}
	}
	return &TreeNode{
		Val:   nums[mx],
		Left:  construct(nums, l, mx-1),
		Right: construct(nums, mx+1, r),
	}
}