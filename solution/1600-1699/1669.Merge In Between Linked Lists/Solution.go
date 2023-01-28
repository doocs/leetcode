/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeInBetween(list1 *ListNode, a int, b int, list2 *ListNode) *ListNode {
	p, q := list1, list1
	for ; a > 1; a-- {
		p = p.Next
	}
	for ; b > 0; b-- {
		q = q.Next
	}
	p.Next = list2
	for p.Next != nil {
		p = p.Next
	}
	p.Next = q.Next
	q.Next = nil
	return list1
}