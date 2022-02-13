/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteNode(head *ListNode, val int) *ListNode {
	dummy := &ListNode{0, head}
	pre := dummy
	for ; pre.Next != nil && pre.Next.Val != val; pre = pre.Next {
	}
	if pre.Next != nil {
		pre.Next = pre.Next.Next
	}
	return dummy.Next
}