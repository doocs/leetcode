/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func getDirections(root *TreeNode, startValue int, destValue int) string {
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

	pathToStart := []byte{}
	pathToDest := []byte{}
	dfs(root, startValue, &pathToStart)
	dfs(root, destValue, &pathToDest)
	i := 0
	for i < len(pathToStart) && i < len(pathToDest) && pathToStart[i] == pathToDest[i] {
		i++
	}
	return string(bytes.Repeat([]byte{'U'}, len(pathToStart)-i)) + string(pathToDest[i:])
}
