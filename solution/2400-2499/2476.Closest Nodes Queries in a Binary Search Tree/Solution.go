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
	for _, x := range queries {
		i := sort.SearchInts(nums, x+1) - 1
		j := sort.SearchInts(nums, x)
		mi, mx := -1, -1
		if i >= 0 {
			mi = nums[i]
		}
		if j < len(nums) {
			mx = nums[j]
		}
		ans = append(ans, []int{mi, mx})
	}
	return
}