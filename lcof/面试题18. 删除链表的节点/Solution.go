/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteNode(head *ListNode, val int) *ListNode {
	dummy := &ListNode{0, head}
	for cur := dummy; cur.Next != nil; cur = cur.Next {
		if cur.Next.Val == val {
			cur.Next = cur.Next.Next
			break
		}
	}
	return dummy.Next
}