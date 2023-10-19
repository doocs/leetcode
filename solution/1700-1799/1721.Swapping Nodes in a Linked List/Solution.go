/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func swapNodes(head *ListNode, k int) *ListNode {
	fast := head
	for ; k > 1; k-- {
		fast = fast.Next
	}
	p := fast
	slow := head
	for fast.Next != nil {
		fast, slow = fast.Next, slow.Next
	}
	q := slow
	p.Val, q.Val = q.Val, p.Val
	return head
}