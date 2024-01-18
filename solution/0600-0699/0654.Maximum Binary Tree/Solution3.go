/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func constructMaximumBinaryTree(nums []int) *TreeNode {
	stk := []*TreeNode{}
	for _, v := range nums {
		node := &TreeNode{Val: v}
		var last *TreeNode
		for len(stk) > 0 && stk[len(stk)-1].Val < v {
			last = stk[len(stk)-1]
			stk = stk[:len(stk)-1]
		}
		node.Left = last
		if len(stk) > 0 {
			stk[len(stk)-1].Right = node
		}
		stk = append(stk, node)
	}
	return stk[0]
}