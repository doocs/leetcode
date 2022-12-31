/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseBetween(head *ListNode, left int, right int) *ListNode {
	if head.Next == nil || left == right {
		return head
	}
	dummy := &ListNode{0, head}
	pre := dummy
	for i := 0; i < left-1; i++ {
		pre = pre.Next
	}
	p, q := pre, pre.Next
	cur := q
	for i := 0; i < right-left+1; i++ {
		t := cur.Next
		cur.Next = pre
		pre = cur
		cur = t
	}
	p.Next = pre
	q.Next = cur
	return dummy.Next
}