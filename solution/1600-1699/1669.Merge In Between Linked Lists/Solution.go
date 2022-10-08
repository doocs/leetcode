/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func mergeInBetween(list1 *ListNode, a int, b int, list2 *ListNode) *ListNode {
	p, q := list1, list1
	for i := 0; i < a-1; i++ {
		p = p.Next
	}
	for i := 0; i < b+1; i++ {
		q = q.Next
	}
	t := list2
	for t.Next != nil {
		t = t.Next
	}
	t.Next = q
	p.Next = list2
	return list1
}