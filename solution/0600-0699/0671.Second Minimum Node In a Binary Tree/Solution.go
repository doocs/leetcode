/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func findSecondMinimumValue(root *TreeNode) int {
	ans := -1

	var dfs func(root *TreeNode, val int)
	dfs = func(root *TreeNode, val int) {
		if root == nil {
			return
		}
		dfs(root.Left, val)
		dfs(root.Right, val)
		if root.Val > val {
			if ans == -1 {
				ans = root.Val
			} else {
				ans = min(ans, root.Val)
			}
		}
	}

	dfs(root, root.Val)
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}