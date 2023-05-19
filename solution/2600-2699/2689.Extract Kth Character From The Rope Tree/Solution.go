/**
 * Definition for a rope tree node.
 * type RopeTreeNode struct {
 * 	   len   int
 * 	   val   string
 * 	   left  *RopeTreeNode
 * 	   right *RopeTreeNode
 * }
 */
func getKthCharacter(root *RopeTreeNode, k int) byte {
	var dfs func(root *RopeTreeNode) string
	dfs = func(root *RopeTreeNode) string {
		if root == nil {
			return ""
		}
		if root.len == 0 {
			return root.val
		}
		left, right := dfs(root.left), dfs(root.right)
		return left + right
	}
	return dfs(root)[k-1]
}