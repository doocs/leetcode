/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteNodes(head *ListNode, m int, n int) *ListNode {
	pre := head
	for pre != nil {
		for i := 0; i < m-1 && pre != nil; i++ {
			pre = pre.Next
		}
		if pre == nil {
			return head
		}
		cur := pre
		for i := 0; i < n && cur != nil; i++ {
			cur = cur.Next
		}
		pre.Next = nil
		if cur != nil {
			pre.Next = cur.Next
		}
		pre = pre.Next
	}
	return head
}