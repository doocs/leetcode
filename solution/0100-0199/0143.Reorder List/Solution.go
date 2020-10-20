/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func reorderList(head *ListNode)  {
    // 判空
    if head == nil {
        return 
    }
    //切片存放指针
    deq := []*ListNode{}
    for head != nil {
        deq = append(deq, head)
        head = head.Next
    }

    left, right := 0, len(deq) -1
    for left < right {
        deq[left].Next = deq[right]
        left++
        deq[right].Next = deq[left]
        right--
    }
    deq[left].Next = nil
}
