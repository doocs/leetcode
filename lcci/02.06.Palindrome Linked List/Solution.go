/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func isPalindrome(head *ListNode) bool {
	if head == nil {
		return true
	}
	slow, fast := head, head.Next
	for fast != nil && fast.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
	}
	p := slow.Next
	slow.Next = nil
	dummy := &ListNode{}
	for p != nil {
		next := p.Next
		p.Next = dummy.Next
		dummy.Next = p
		p = next
	}
	p = dummy.Next
	for p != nil {
		if head.Val != p.Val {
			return false
		}
		head = head.Next
		p = p.Next
	}
	return true
}