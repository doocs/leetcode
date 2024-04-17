/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func sumOfLeftLeaves(root *TreeNode) (ans int) {
	if root == nil {
		return 0
	}
	stk := []*TreeNode{root}
	for len(stk) > 0 {
		root = stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if root.Left != nil {
			if root.Left.Left == root.Left.Right {
				ans += root.Left.Val
			} else {
				stk = append(stk, root.Left)
			}
		}
		if root.Right != nil {
			stk = append(stk, root.Right)
		}
	}
	return
}