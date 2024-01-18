/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseKGroup(head *ListNode, k int) *ListNode {
	var dummy *ListNode = &ListNode{}
	p, cur := dummy, head
	for cur != nil {
		start := cur
		for i := 0; i < k; i++ {
			if cur == nil {
				p.Next = start
				return dummy.Next
			}
			cur = cur.Next
		}
		p.Next, p = reverse(start, cur), start
	}
	return dummy.Next
}

func reverse(start, end *ListNode) *ListNode {
	var pre *ListNode = nil
	for start != end {
		tmp := start.Next
		start.Next, pre = pre, start
		start = tmp
	}
	return pre
}