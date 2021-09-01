func reverseList(head *ListNode) *ListNode {
    if head == nil ||head.Next == nil {
        return head
    }
    dummyHead := &ListNode{}
    cur := head
    for cur != nil {
        tmp := cur.Next
        cur.Next = dummyHead.Next
        dummyHead.Next = cur
        cur = tmp
    }
    return dummyHead.Next
}