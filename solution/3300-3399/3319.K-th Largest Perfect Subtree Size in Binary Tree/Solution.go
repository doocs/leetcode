/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func kthLargestPerfectSubtree(root *TreeNode, k int) int {
	nums := []int{}
	var dfs func(*TreeNode) int
	dfs = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := dfs(root.Left), dfs(root.Right)
		if l < 0 || l != r {
			return -1
		}
		cnt := l + r + 1
		nums = append(nums, cnt)
		return cnt
	}
	dfs(root)
	if len(nums) < k {
		return -1
	}
	sort.Sort(sort.Reverse(sort.IntSlice(nums)))
	return nums[k-1]
}
