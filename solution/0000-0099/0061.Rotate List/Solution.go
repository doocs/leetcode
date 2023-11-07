/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func rotateRight(head *ListNode, k int) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	cur := head
	n := 0
	for cur != nil {
		cur = cur.Next
		n++
	}
	k %= n
	if k == 0 {
		return head
	}
	fast, slow := head, head
	for i := 0; i < k; i++ {
		fast = fast.Next
	}
	for fast.Next != nil {
		fast = fast.Next
		slow = slow.Next
	}
	ans := slow.Next
	slow.Next = nil
	fast.Next = head
	return ans
}