/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reverseKGroup(head *ListNode, k int) *ListNode {
	start, end := head, head
	for i := 0; i < k; i++ {
		if end == nil {
			return head
		}
		end = end.Next
	}
	res := reverse(start, end)
	start.Next = reverseKGroup(end, k)
	return res
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
