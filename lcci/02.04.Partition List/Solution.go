/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func partition(head *ListNode, x int) *ListNode {
	left, right := &ListNode{}, &ListNode{}
	p1, p2 := left, right
	for ; head != nil; head = head.Next {
		if head.Val < x {
			p1.Next = head
			p1 = p1.Next
		} else {
			p2.Next = head
			p2 = p2.Next
		}
	}
	p1.Next = right.Next
	p2.Next = nil
	return left.Next
}