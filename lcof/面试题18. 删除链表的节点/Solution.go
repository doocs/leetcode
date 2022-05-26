func deleteNode(head *ListNode, val int) *ListNode {
    res := &ListNode{
        Val: 0,
        Next: head,
    }
    pre := res
    cur := res.Next
    for cur != nil {
        if cur.Val == val {
            pre.Next = cur.Next
            return res.Next
        }
        cur = cur.Next
        pre = pre.Next
    }
    return res.Next
}