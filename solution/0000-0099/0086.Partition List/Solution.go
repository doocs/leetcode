/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func partition(head *ListNode, x int) *ListNode {
	d1, d2 := &ListNode{}, &ListNode{}
	t1, t2 := d1, d2
	for head != nil {
		if head.Val < x {
			t1.Next = head
			t1 = t1.Next
		} else {
			t2.Next = head
			t2 = t2.Next
		}
		head = head.Next
	}
	t1.Next = d2.Next
	t2.Next = nil
	return d1.Next
}