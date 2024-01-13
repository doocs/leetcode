/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func removeNodes(head *ListNode) *ListNode {
	dummy := &ListNode{1 << 30, head}
	stk := []*ListNode{dummy}
	for cur := head; cur != nil; cur = cur.Next {
		for stk[len(stk)-1].Val < cur.Val {
			stk = stk[:len(stk)-1]
		}
		stk[len(stk)-1].Next = cur
		stk = append(stk, cur)
	}
	return dummy.Next
}