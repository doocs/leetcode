/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func inorderTraversal(root *TreeNode) (ans []int) {
	stk := []*TreeNode{}
	for root != nil || len(stk) > 0 {
		if root != nil {
			stk = append(stk, root)
			root = root.Left
		} else {
			root = stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			ans = append(ans, root.Val)
			root = root.Right
		}
	}
	return
}