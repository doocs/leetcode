/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func getDirections(root *TreeNode, startValue int, destValue int) string {
	var lca func(node *TreeNode, p, q int) *TreeNode
	lca = func(node *TreeNode, p, q int) *TreeNode {
		if node == nil || node.Val == p || node.Val == q {
			return node
		}
		left := lca(node.Left, p, q)
		right := lca(node.Right, p, q)
		if left != nil && right != nil {
			return node
		}
		if left != nil {
			return left
		}
		return right
	}
	var dfs func(node *TreeNode, x int, path *[]byte) bool
	dfs = func(node *TreeNode, x int, path *[]byte) bool {
		if node == nil {
			return false
		}
		if node.Val == x {
			return true
		}
		*path = append(*path, 'L')
		if dfs(node.Left, x, path) {
			return true
		}
		(*path)[len(*path)-1] = 'R'
		if dfs(node.Right, x, path) {
			return true
		}
		*path = (*path)[:len(*path)-1]
		return false
	}

	node := lca(root, startValue, destValue)
	pathToStart := []byte{}
	pathToDest := []byte{}
	dfs(node, startValue, &pathToStart)
	dfs(node, destValue, &pathToDest)
	return string(bytes.Repeat([]byte{'U'}, len(pathToStart))) + string(pathToDest)
}
