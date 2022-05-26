/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func swapPairs(head *ListNode) *ListNode {
    dummy := &ListNode{0, head}
    pre, cur := dummy, head
    for cur != nil && cur.Next != nil {
        t := cur.Next
        cur.Next = t.Next
        t.Next = cur
        pre.Next = t
        pre = cur
        cur = cur.Next
    }
    return dummy.Next
}