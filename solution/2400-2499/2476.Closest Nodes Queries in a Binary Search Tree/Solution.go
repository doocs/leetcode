/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func closestNodes(root *TreeNode, queries []int) (ans [][]int) {
	nums := []int{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		dfs(root.Left)
		nums = append(nums, root.Val)
		dfs(root.Right)
	}
	dfs(root)
	n := len(nums)
	for _, v := range queries {
		i := sort.SearchInts(nums, v+1) - 1
		j := sort.SearchInts(nums, v)
		mi, mx := -1, -1
		if i >= 0 && i < n {
			mi = nums[i]
		}
		if j >= 0 && j < n {
			mx = nums[j]
		}
		ans = append(ans, []int{mi, mx})
	}
	return
}