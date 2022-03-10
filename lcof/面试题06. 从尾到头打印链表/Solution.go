/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reversePrint(head *ListNode) []int {
	ans := []int{}
	for head != nil {
		ans = append([]int{head.Val}, ans...)
		head = head.Next
	}
	return ans
}