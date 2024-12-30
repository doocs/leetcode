/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseKGroup(head *ListNode, k int) *ListNode {
	dummy := &ListNode{Next: head}
	pre := dummy

	for pre != nil {
		cur := pre
		for i := 0; i < k; i++ {
			cur = cur.Next
			if cur == nil {
				return dummy.Next
			}
		}

		node := pre.Next
		nxt := cur.Next
		cur.Next = nil
		pre.Next = reverse(node)
		node.Next = nxt
		pre = node
	}
	return dummy.Next
}

func reverse(head *ListNode) *ListNode {
	var dummy *ListNode
	cur := head
	for cur != nil {
		nxt := cur.Next
		cur.Next = dummy
		dummy = cur
		cur = nxt
	}
	return dummy
}
