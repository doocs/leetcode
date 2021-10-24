/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func sortLinkedList(head *ListNode) *ListNode {
	prev, curr := head, head.Next
	for curr != nil {
		if curr.Val < 0 {
			t := curr.Next
			prev.Next = t
			curr.Next = head
			head = curr
			curr = t
		} else {
			prev, curr = curr, curr.Next
		}
	}
	return head
}