/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func leafSimilar(root1 *TreeNode, root2 *TreeNode) bool {
	var dfs func(*TreeNode) []int
	dfs = func(root *TreeNode) []int {
		if root == nil {
			return []int{}
		}
		ans := dfs(root.Left)
		ans = append(ans, dfs(root.Right)...)
		if len(ans) == 0 {
			ans = append(ans, root.Val)
		}
		return ans
	}
	return reflect.DeepEqual(dfs(root1), dfs(root2))
}