/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func nextLargerNodes(head *ListNode) []int {
	nums := []int{}
	for ; head != nil; head = head.Next {
		nums = append(nums, head.Val)
	}
	stk := []int{}
	n := len(nums)
	ans := make([]int, n)
	for i := n - 1; i >= 0; i-- {
		for len(stk) > 0 && stk[len(stk)-1] <= nums[i] {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			ans[i] = stk[len(stk)-1]
		}
		stk = append(stk, nums[i])
	}
	return ans
}