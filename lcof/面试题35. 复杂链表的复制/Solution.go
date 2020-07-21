func copyRandomList(head *Node) *Node {
    if head == nil {
        return nil
    }
    cur, next := head,head
    //完成对当前节点的复制
    for cur != nil {
        next = cur.Next
        cur.Next = &Node{
            Val: cur.Val,
            Next: next,
            Random: nil,
        }
        cur = next
    }
    //设置复制节点的random节点
    cur = head
    curCopy := head
    for cur != nil {
        next = cur.Next.Next
        curCopy = cur.Next
        if cur.Random == nil {
            curCopy.Random = nil
        } else {
            curCopy.Random = cur.Random.Next
        }
        cur = next
    }
    res := head.Next
    cur = head
    for cur != nil {
        next = cur.Next.Next
        curCopy = cur.Next
        cur.Next = next
        if next != nil {
            curCopy.Next = next.Next
        } else {
            curCopy.Next = nil
        }
        cur = cur.Next
    }
    return res

}