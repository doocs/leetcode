/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func longestZigZag(root *TreeNode) int {
	ans := 0
	type frame struct {
		node *TreeNode
		l, r int
	}
	stk := []frame{{root, 0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if cur.node == nil {
			continue
		}
		ans = max(ans, max(cur.l, cur.r))
		stk = append(stk, frame{cur.node.Right, 0, cur.l + 1}, frame{cur.node.Left, cur.r + 1, 0})
	}
	return ans
}
