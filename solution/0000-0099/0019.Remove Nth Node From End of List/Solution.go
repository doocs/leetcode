/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func removeNthFromEnd(head *ListNode, n int) *ListNode {
	node0 := &ListNode{Val:0}
	node0.Next = head
	left := node0
	right := node0
	for n>0 {
		right = right.Next
		n--
	}	
	for right.Next != nil {
		left = left.Next
		right = right.Next
	}
	left.Next = left.Next.Next
    return node0.Next
}