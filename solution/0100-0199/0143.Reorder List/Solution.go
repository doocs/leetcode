/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reorderList(head *ListNode) {
	// 快慢指针找到链表中点
	fast, slow := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow, fast = slow.Next, fast.Next.Next
	}

	// cur 指向右半部分链表
	cur := slow.Next
	slow.Next = nil

	// 反转右半部分链表
	var pre *ListNode
	for cur != nil {
		t := cur.Next
		cur.Next = pre
		pre, cur = cur, t
	}
	cur = head

	// 此时 cur, pre 分别指向链表左右两半的第一个节点
	// 合并
	for pre != nil {
		t := pre.Next
		pre.Next = cur.Next
		cur.Next = pre
		cur, pre = pre.Next, t
	}
}