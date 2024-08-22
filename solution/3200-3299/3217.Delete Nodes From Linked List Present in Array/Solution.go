/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func modifiedList(nums []int, head *ListNode) *ListNode {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	dummy := &ListNode{Next: head}
	for pre := dummy; pre.Next != nil; {
		if s[pre.Next.Val] {
			pre.Next = pre.Next.Next
		} else {
			pre = pre.Next
		}
	}
	return dummy.Next
}