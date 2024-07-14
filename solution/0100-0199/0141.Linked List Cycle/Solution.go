/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func hasCycle(head *ListNode) bool {
	s := map[*ListNode]bool{}
	for ; head != nil; head = head.Next {
		if s[head] {
			return true
		}
		s[head] = true
	}
	return false
}