func getKthFromEnd(head *ListNode, k int) *ListNode {
    tmp := head
    for tmp != nil && k > 0{
        tmp = tmp.Next
        k--
    }
    slow := head
    fast := tmp
    for fast != nil {
        fast = fast.Next
        slow = slow.Next
    }
    return slow
}