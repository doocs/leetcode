/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reversePrint(head *ListNode) (ans []int) {
	if head == nil {
		return
	}
	ans = reversePrint(head.Next)
	ans = append(ans, head.Val)
	return
}