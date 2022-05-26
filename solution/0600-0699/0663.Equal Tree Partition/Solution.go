/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func checkEqualTree(root *TreeNode) bool {
	var seen []int
	var sum func(root *TreeNode) int
	sum = func(root *TreeNode) int {
		if root == nil {
			return 0
		}
		l, r := sum(root.Left), sum(root.Right)
		s := l + r + root.Val
		seen = append(seen, s)
		return s
	}

	s := sum(root)
	if s%2 != 0 {
		return false
	}
	seen = seen[:len(seen)-1]
	for _, v := range seen {
		if v == s/2 {
			return true
		}
	}
	return false
}