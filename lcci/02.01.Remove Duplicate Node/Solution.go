func removeDuplicateNodes(head *ListNode) *ListNode {
	if head == nil {
		return nil
	}
	vis := map[int]bool{head.Val: true}
	p := head
	for p.Next != nil {
		if vis[p.Next.Val] {
			p.Next = p.Next.Next
		} else {
			vis[p.Next.Val] = true
			p = p.Next
		}
	}
	return head
}
