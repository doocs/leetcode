
/** class ListNode {
*    var val: Int
*    var next: ListNode?
*    init() { self.val = 0; self.next = nil }
*    init(_ val: Int) { self.val = val; self.next = nil }
*    init(_ val: Int, _ next: ListNode?) { self.val = val; self.next = next }
* }
*/

class Solution {
    func sortList(_ head: ListNode?) -> ListNode? {
        guard let head = head, head.next != nil else {
            return head
        }
        
        var slow: ListNode? = head
        var fast: ListNode? = head.next
        
        while fast != nil && fast?.next != nil {
            slow = slow?.next
            fast = fast?.next?.next
        }
        
        let mid = slow?.next
        slow?.next = nil
        
        let left = sortList(head)
        let right = sortList(mid)
        
        return merge(left, right)
    }
    
    private func merge(_ l1: ListNode?, _ l2: ListNode?) -> ListNode? {
        let dummy = ListNode()
        var cur = dummy
        var l1 = l1, l2 = l2
        
        while let node1 = l1, let node2 = l2 {
            if node1.val <= node2.val {
                cur.next = node1
                l1 = node1.next
            } else {
                cur.next = node2
                l2 = node2.next
            }
            cur = cur.next!
        }
        
        cur.next = l1 ?? l2
        return dummy.next
    }
}