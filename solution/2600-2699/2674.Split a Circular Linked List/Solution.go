/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func splitCircularLinkedList(list *ListNode) []*ListNode {
	a, b := list, list
	for b.Next != list && b.Next.Next != list {
		a = a.Next
		b = b.Next.Next
	}
	if b.Next != list {
		b = b.Next
	}
	list2 := a.Next
	b.Next = list2
	a.Next = list
	return []*ListNode{list, list2}
}