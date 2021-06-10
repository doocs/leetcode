/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func reorderList(head *ListNode)  {
    if head == nil || head.Next == nil {
        return
    }
    slow, fast := head, head.Next
    for fast != nil && fast.Next != nil {
        slow, fast = slow.Next, fast.Next.Next
    }

    cur := slow.Next
    slow.Next = nil

    var pre *ListNode
    for cur != nil {
        t := cur.Next
        cur.Next = pre
        pre, cur = cur, t
    }
    cur = head

    for pre != nil {
        t := pre.Next
        pre.Next = cur.Next
        cur.Next = pre
        cur, pre = pre.Next, t
    }
}