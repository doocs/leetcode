/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func splitListToParts(head *ListNode, k int) []*ListNode {
	n := 0
	for cur := head; cur != nil; cur = cur.Next {
		n++
	}

	cnt := n / k
	mod := n % k
	ans := make([]*ListNode, k)
	cur := head

	for i := 0; i < k && cur != nil; i++ {
		ans[i] = cur
		m := cnt
		if i < mod {
			m++
		}
		for j := 1; j < m; j++ {
			cur = cur.Next
		}
		next := cur.Next
		cur.Next = nil
		cur = next
	}

	return ans
}