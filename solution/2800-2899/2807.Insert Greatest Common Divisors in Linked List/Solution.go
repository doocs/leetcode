/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func insertGreatestCommonDivisors(head *ListNode) *ListNode {
	for pre, cur := head, head.Next; cur != nil; cur = cur.Next {
		x := gcd(pre.Val, cur.Val)
		pre.Next = &ListNode{x, cur}
		pre = cur
	}
	return head
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}