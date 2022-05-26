/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func detectCycle(head *ListNode) *ListNode {
    slow, fast := head, head
    hasCycle := false
    for !hasCycle && fast != nil && fast.Next != nil {
        slow, fast = slow.Next, fast.Next.Next
        hasCycle = slow == fast
    }
    if !hasCycle {
        return nil
    }
    p := head
    for p != slow {
        p, slow = p.Next, slow.Next
    }
    return p
}