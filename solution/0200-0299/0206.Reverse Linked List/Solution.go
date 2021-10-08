/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseList(head *ListNode) *ListNode {
	var pre *ListNode
	for p := head; p != nil; {
		q := p.Next
		p.Next = pre
		pre = p
		p = q
	}
	return pre
}