/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func nodesBetweenCriticalPoints(head *ListNode) []int {
	ans := []int{1 << 30, 0}
	first, last := -1, -1
	for i := 0; head.Next.Next != nil; head, i = head.Next, i+1 {
		a, b, c := head.Val, head.Next.Val, head.Next.Next.Val
		if b < min(a, c) || b > max(a, c) {
			if last == -1 {
				first, last = i, i
			} else {
				ans[0] = min(ans[0], i-last)
				last = i
				ans[1] = max(ans[1], last-first)
			}
		}
	}
	if first == last {
		return []int{-1, -1}
	}
	return ans
}