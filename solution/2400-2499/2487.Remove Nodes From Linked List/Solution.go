/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func removeNodes(head *ListNode) *ListNode {
	nums := []int{}
	for head != nil {
		nums = append(nums, head.Val)
		head = head.Next
	}
	stk := []int{}
	for _, v := range nums {
		for len(stk) > 0 && stk[len(stk)-1] < v {
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, v)
	}
	dummy := &ListNode{}
	head = dummy
	for _, v := range stk {
		head.Next = &ListNode{Val: v}
		head = head.Next
	}
	return dummy.Next
}