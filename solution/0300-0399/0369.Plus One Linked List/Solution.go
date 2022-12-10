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
	target = target.Next
	for target != nil {
		target.Val = 0
		target = target.Next
	}
	if dummy.Val == 1 {
		return dummy
	}
	return dummy.Next
}