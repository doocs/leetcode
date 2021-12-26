/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func pseudoPalindromicPaths(root *TreeNode) int {
	ans := 0
	counter := make([]int, 10)
	var dfs func(root *TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		counter[root.Val]++
		if root.Left == nil && root.Right == nil {
			n := 0
			for i := 1; i < 10; i++ {
				if counter[i]%2 == 1 {
					n++
				}
			}
			if n < 2 {
				ans++
			}
		} else {
			dfs(root.Left)
			dfs(root.Right)
		}
		counter[root.Val]--
	}
	dfs(root)
	return ans
}