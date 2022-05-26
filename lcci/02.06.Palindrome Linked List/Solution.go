func isPalindrome(head *ListNode) bool {
	if head == nil {
		return true
	}
	m := mid(head)
	temp := reverse(m.Next)
	m.Next = nil
	p, q := head, temp
	res := true
	for p != nil && q != nil {
		if p.Val != q.Val {
			res = false
			break
		}
		p = p.Next
		q = q.Next
	}
	m.Next = reverse(temp)
	return res
}

func mid(head *ListNode) *ListNode {
	slow, fast := head, head.Next
	for fast != nil && fast.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}
	return slow
}

func reverse(head *ListNode) *ListNode {
	var prev *ListNode = nil
	for head != nil {
		temp := head.Next
		head.Next = prev
		prev = head
		head = temp
	}
	return prev
}
