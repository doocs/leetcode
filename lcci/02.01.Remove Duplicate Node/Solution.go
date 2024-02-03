/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func removeDuplicateNodes(head *ListNode) *ListNode {
	vis := map[int]bool{}
	pre := &ListNode{0, head}
	for pre.Next != nil {
		if vis[pre.Next.Val] {
			pre.Next = pre.Next.Next
		} else {
			vis[pre.Next.Val] = true
			pre = pre.Next
		}
	}
	return head
}