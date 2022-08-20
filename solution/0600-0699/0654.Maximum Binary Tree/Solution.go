/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructMaximumBinaryTree(nums []int) *TreeNode {
	var dfs func(l, r int) *TreeNode
	dfs = func(l, r int) *TreeNode {
		if l > r {
			return nil
		}
		i := l
		for j := l; j <= r; j++ {
			if nums[i] < nums[j] {
				i = j
			}
		}
		root := &TreeNode{Val: nums[i]}
		root.Left = dfs(l, i-1)
		root.Right = dfs(i+1, r)
		return root
	}
	return dfs(0, len(nums)-1)
}