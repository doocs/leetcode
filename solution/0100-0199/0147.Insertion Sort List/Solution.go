/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func insertionSortList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	dummy := &ListNode{head.Val, head}
	pre, cur := dummy, head
	for cur != nil {
		if pre.Val <= cur.Val {
			pre = cur
			cur = cur.Next
			continue
		}
		p := dummy
		for p.Next.Val <= cur.Val {
			p = p.Next
		}
		t := cur.Next
		cur.Next = p.Next
		p.Next = cur
		pre.Next = t
		cur = t
	}
	return dummy.Next
}
