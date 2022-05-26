/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	s1, s2 := arraystack.New(), arraystack.New()
	for l1 != nil {
		s1.Push(l1.Val)
		l1 = l1.Next
	}
	for l2 != nil {
		s2.Push(l2.Val)
		l2 = l2.Next
	}
	carry, dummy := 0, new(ListNode)
	for !s1.Empty() || !s2.Empty() || carry > 0 {
		v, ok := s1.Pop()
		if ok {
			carry += v.(int)
		}
		v, ok = s2.Pop()
		if ok {
			carry += v.(int)
		}
		node := &ListNode{Val: carry % 10, Next: dummy.Next}
		dummy.Next = node
		carry /= 10
	}
	return dummy.Next
}