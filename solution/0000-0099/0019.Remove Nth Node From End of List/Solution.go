/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func removeNthFromEnd(head *ListNode, n int) *ListNode {
	dummy := &ListNode{0, head}
	fast, slow := dummy, dummy
	for ; n > 0; n-- {
		fast = fast.Next
	}
	for fast.Next != nil {
		slow, fast = slow.Next, fast.Next
	}
	slow.Next = slow.Next.Next
	return dummy.Next
}