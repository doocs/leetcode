/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func pairSum(head *ListNode) int {
	reverse := func(head *ListNode) *ListNode {
		dummy := &ListNode{}
		curr := head
		for curr != nil {
			next := curr.Next
			curr.Next = dummy.Next
			dummy.Next = curr
			curr = next
		}
		return dummy.Next
	}
	slow, fast := head, head.Next
	for fast != nil && fast.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
	}
	pa := head
	q := slow.Next
	slow.Next = nil
	pb := reverse(q)
	ans := 0
	for pa != nil {
		ans = max(ans, pa.Val+pb.Val)
		pa = pa.Next
		pb = pb.Next
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}