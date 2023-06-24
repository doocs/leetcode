/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func listOfDepth(tree *TreeNode) (ans []*ListNode) {
	q := []*TreeNode{tree}
	for len(q) > 0 {
		dummy := &ListNode{}
		cur := dummy
		for k := len(q); k > 0; k-- {
			node := q[0]
			q = q[1:]
			cur.Next = &ListNode{Val: node.Val}
			cur = cur.Next
			if node.Left != nil {
				q = append(q, node.Left)
			}
			if node.Right != nil {
				q = append(q, node.Right)
			}
		}
		ans = append(ans, dummy.Next)
	}
	return
}