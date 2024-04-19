/**
* public class ListNode {
*    var val: Int
*    var next: ListNode?
*    init(_ x: Int) {
*        self.val = x
*        self.next = nil
*    }
* }
*/

class Solution {
    func isPalindrome(_ head: ListNode?) -> Bool {
        if head == nil {
            return true
        }
        
        var slow = head
        var fast = head?.next
        while fast != nil && fast?.next != nil {
            slow = slow?.next
            fast = fast?.next?.next
        }
        
        var p = slow?.next
        slow?.next = nil
        var dummy = ListNode(0)
        
        while p != nil {
            let next = p?.next
            p?.next = dummy.next
            dummy.next = p
            p = next
        }
        
        p = dummy.next
        var currentHead = head
        while p != nil {
            if currentHead?.val != p?.val {
                return false
            }
            currentHead = currentHead?.next
            p = p?.next
        }
        
        return true
    }
}
