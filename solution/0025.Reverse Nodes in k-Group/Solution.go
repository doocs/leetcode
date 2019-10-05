/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func reverseKGroup(head *ListNode, k int) *ListNode {
	start := head
	var h *ListNode = nil
	var t *ListNode = nil
	ok := false
	if h, t, ok = revert(start, k); !ok {
		return head
	}
	r := h
	p := t
	print(2, r)
	for ok {
		start = t.Next
		h, t, ok = revert(start, k)
		p.Next = h
		p = t
	}
	return r
}

func revert(head *ListNode, k int) (h, t *ListNode, ok bool) {
	c := head
	if !check(c, k) {
		return head, nil, false
	}

	t = head
	var p *ListNode = nil
	for k > 0 {
		temp := head
		head = head.Next
		temp.Next = p
		p = temp
		k--
	}
	h = p
	t.Next = head
	ok = true
	print(11, h)
	return
}

func check(head *ListNode, k int) bool {
	for k > 0 {
		if head == nil {
			return false
		}
		k--
		head = head.Next
	}
	return true
}