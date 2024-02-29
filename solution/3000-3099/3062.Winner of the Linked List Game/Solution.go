/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func gameResult(head *ListNode) string {
	var odd, even int
	for ; head != nil; head = head.Next.Next {
		a, b := head.Val, head.Next.Val
		if a < b {
			odd++
		}
		if a > b {
			even++
		}
	}
	if odd > even {
		return "Odd"
	}
	if odd < even {
		return "Even"
	}
	return "Tie"
}