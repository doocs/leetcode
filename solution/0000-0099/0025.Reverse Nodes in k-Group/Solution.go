/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseKGroup(head *ListNode, k int) *ListNode {
	dummy := &ListNode{0, head}
	pre := dummy
	cur := dummy
	for cur.Next != nil {
		for i := 0; i < k && cur != nil; i++ {
			cur = cur.Next
		}
		if cur == nil {
			return dummy.Next
		}
		t := cur.Next
		cur.Next = nil
		start := pre.Next
		pre.Next = reverseList(start)
		start.Next = t
		pre = start
		cur = pre
	}
	return dummy.Next
}

func reverseList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	dummyHead := &ListNode{}
	cur := head
	for cur != nil {
		tmp := cur.Next
		cur.Next = dummyHead.Next
		dummyHead.Next = cur
		cur = tmp
	}
	return dummyHead.Next
}