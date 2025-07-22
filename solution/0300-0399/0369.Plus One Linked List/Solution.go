/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func plusOne(head *ListNode) *ListNode {
	dummy := &ListNode{0, head}
	target := dummy
	for head != nil {
		if head.Val != 9 {
			target = head
		}
		head = head.Next
	}
	target.Val++
	for target = target.Next; target != nil; target = target.Next {
		target.Val = 0
	}
	if dummy.Val == 1 {
		return dummy
	}
	return dummy.Next
}