/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func getDecimalValue(head *ListNode) (ans int) {
	for ; head != nil; head = head.Next {
		ans = ans<<1 | head.Val
	}
	return
}