/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func smallestFromLeaf(root *TreeNode) string {
	ans := ""
	var dfs func(root *TreeNode, path string)
	dfs = func(root *TreeNode, path string) {
		if root == nil {
			return
		}
		path = string('a'+root.Val) + path
		if root.Left == nil && root.Right == nil {
			if ans == "" || path < ans {
				ans = path
			}
			return
		}
		dfs(root.Left, path)
		dfs(root.Right, path)
	}

	dfs(root, "")
	return ans
}