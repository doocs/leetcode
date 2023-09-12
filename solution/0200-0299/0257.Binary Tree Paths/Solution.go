/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func binaryTreePaths(root *TreeNode) (ans []string) {
	t := []string{}
	var dfs func(*TreeNode)
	dfs = func(root *TreeNode) {
		if root == nil {
			return
		}
		t = append(t, strconv.Itoa(root.Val))
		if root.Left == nil && root.Right == nil {
			ans = append(ans, strings.Join(t, "->"))
		} else {
			dfs(root.Left)
			dfs(root.Right)
		}
		t = t[:len(t)-1]
	}
	dfs(root)
	return
}