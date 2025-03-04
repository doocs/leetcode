/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func partition(head *ListNode, x int) *ListNode {
	l, r := &ListNode{}, &ListNode{}
	tl, tr := l, r
	for ; head != nil; head = head.Next {
		if head.Val < x {
			tl.Next = head
			tl = tl.Next
		} else {
			tr.Next = head
			tr = tr.Next
		}
	}
	tr.Next = nil
	tl.Next = r.Next
	return l.Next
}
