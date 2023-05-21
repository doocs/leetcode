/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxValue(root *TreeNode, k int) int {
	var dfs func(*TreeNode) []int
	dfs = func(node *TreeNode) []int {
		ans := make([]int, k+1)
		if node == nil {
			return ans
		}
		l := dfs(node.Left)
		r := dfs(node.Right)
		ans[0] = max(l...) + max(r...)
		for i := 0; i < k; i++ {
			for j := 0; j < k-i; j++ {
				ans[i+j+1] = max(ans[i+j+1], l[i]+r[j]+node.Val)
			}
		}
		return ans
	}
	return max(dfs(root)...)
}

func max(nums ...int) int {
	ans := nums[0]
	for _, x := range nums {
		if x > ans {
			ans = x
		}
	}
	return ans
}