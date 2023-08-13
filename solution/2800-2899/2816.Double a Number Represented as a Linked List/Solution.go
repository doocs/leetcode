/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func doubleIt(head *ListNode) *ListNode {
	head = reverse(head)
	dummy := &ListNode{}
	cur := dummy
	mul, carry := 2, 0
	for head != nil {
		x := head.Val*mul + carry
		carry = x / 10
		cur.Next = &ListNode{Val: x % 10}
		cur = cur.Next
		head = head.Next
	}
	if carry > 0 {
		cur.Next = &ListNode{Val: carry}
	}
	return reverse(dummy.Next)
}

func reverse(head *ListNode) *ListNode {
	dummy := &ListNode{}
	cur := head
	for cur != nil {
		next := cur.Next
		cur.Next = dummy.Next
		dummy.Next = cur
		cur = next
	}
	return dummy.Next
}