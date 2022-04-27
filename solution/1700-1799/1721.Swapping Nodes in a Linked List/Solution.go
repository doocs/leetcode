/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func swapNodes(head *ListNode, k int) *ListNode {
	fast := head
	for k > 1 {
		fast = fast.Next
		k--
	}
	p := fast
	slow := head
	for fast.Next != nil {
		slow, fast = slow.Next, fast.Next
	}
	q := slow
	p.Val, q.Val = q.Val, p.Val
	return head
}