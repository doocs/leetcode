/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func twoSumBSTs(root1 *TreeNode, root2 *TreeNode, target int) bool {
	nums := [2][]int{}
	var dfs func(*TreeNode, int)
	dfs = func(root *TreeNode, i int) {
		if root == nil {
			return
		}
		dfs(root.Left, i)
		nums[i] = append(nums[i], root.Val)
		dfs(root.Right, i)
	}
	dfs(root1, 0)
	dfs(root2, 1)
	i, j := 0, len(nums[1])-1
	for i < len(nums[0]) && j >= 0 {
		x := nums[0][i] + nums[1][j]
		if x == target {
			return true
		}
		if x < target {
			i++
		} else {
			j--
		}
	}
	return false
}