/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func swapPairs(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	first := head
	second := head.Next
	r := second
    var p *ListNode = nil
	for first != nil && second != nil {
		t := second.Next
		second.Next = first
		first.Next = t
		if p != nil {
			p.Next = second
		}
        p = first
        if t != nil {
			second = t.Next
		} else {
			second = nil
		}
		first = t
	}
	return r
}