/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func nodesBetweenCriticalPoints(head *ListNode) []int {
	prev, curr := head, head.Next
	first, last := 0, 0
	i := 1
	ans := []int{math.MaxInt32, 0}
	for curr.Next != nil {
		if curr.Val < min(prev.Val, curr.Next.Val) || curr.Val > max(prev.Val, curr.Next.Val) {
			if last == 0 {
				first, last = i, i
			} else {
				ans[0] = min(ans[0], i-last)
				ans[1] = i - first
				last = i
			}
		}
		i++
		prev, curr = curr, curr.Next
	}
	if first == last {
		return []int{-1, -1}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}