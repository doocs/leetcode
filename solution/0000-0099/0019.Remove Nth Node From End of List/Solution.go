/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func removeNthFromEnd(head *ListNode, n int) *ListNode {
    dummy := &ListNode{Val:0, Next:head}
    p := dummy
    q := dummy
    for n > 0 {
        p = p.Next
        n--
    }
    for p.Next != nil {
        p = p.Next
        q = q.Next
    }
    q.Next = q.Next.Next
    return dummy.Next
}