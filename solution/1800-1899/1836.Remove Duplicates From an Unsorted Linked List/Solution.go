/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func deleteDuplicatesUnsorted(head *ListNode) *ListNode {
	cnt := map[int]int{}
	for cur := head; cur != nil; cur = cur.Next {
		cnt[cur.Val]++
	}
	dummy := &ListNode{0, head}
	for pre, cur := dummy, head; cur != nil; cur = cur.Next {
		if cnt[cur.Val] > 1 {
			pre.Next = cur.Next
		} else {
			pre = cur
		}
	}
	return dummy.Next
}