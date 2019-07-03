/*
 * @lc app=leetcode.cn id=141 lang=golang
 *  17/17 cases passed (12 ms)， memory usage 4 MB
 */
func hasCycle(head *ListNode) bool {
	if head == nil || head.Next == nil {
		return false
	}
	slow, fast := head, head.Next
	for {
		if fast == nil || fast.Next == nil {
			return false
		}
		if slow == fast {
			return true
		}
		slow, fast = slow.Next, fast.Next.Next
	}
	return false
}