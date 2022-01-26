/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func pairSum(head *ListNode) int {
	var s []int
	for ; head != nil; head = head.Next {
		s = append(s, head.Val)
	}
	ans, n := 0, len(s)
	for i := 0; i < (n >> 1); i++ {
		if ans < s[i]+s[n-i-1] {
			ans = s[i] + s[n-i-1]
		}
	}
	return ans
}