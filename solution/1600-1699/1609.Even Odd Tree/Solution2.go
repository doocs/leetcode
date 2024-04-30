/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isEvenOddTree(root *TreeNode) bool {
	d := map[int]int{}
	var dfs func(*TreeNode, int) bool
	dfs = func(root *TreeNode, i int) bool {
		if root == nil {
			return true
		}
		even := i%2 == 0
		prev, ok := d[i]
		if !ok {
			if even {
				prev = 0
			} else {
				prev = 10000000
			}
		}
		if even && (root.Val%2 == 0 || prev >= root.Val) {
			return false
		}
		if !even && (root.Val%2 == 1 || prev <= root.Val) {
			return false
		}
		d[i] = root.Val
		return dfs(root.Left, i+1) && dfs(root.Right, i+1)
	}
	return dfs(root, 0)
}