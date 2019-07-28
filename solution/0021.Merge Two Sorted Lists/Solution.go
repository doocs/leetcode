/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
 func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
    if l1==nil  {
        return l2
    }
    
    if l2==nil {
        return l1
    }
    
    var p *ListNode
      
    if l1.Val > l2.Val {
        p = l2
        l2 = l2.Next
    }else{
        p = l1
        l1 = l1.Next
    }
    var head *ListNode = p
    p.Next = nil
    
    for l1 != nil && l2 != nil {
        if l1.Val > l2.Val {
            p.Next = l2
            l2 = l2.Next
        }else{
            p.Next = l1
            l1 = l1.Next
        }
        p = p.Next
        p.Next = nil
    }
    
    if l1 != nil{
        p.Next = l1
    }
    if l2 != nil{
        p.Next = l2
    }
    
    return head
}