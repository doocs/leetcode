/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     var val: Int
 *     var next: ListNode?
 *     init() { self.val = 0; self.next = nil; }
 *     init(_ val: Int) { self.val = val; self.next = nil; }
 *     init(_ val: Int, _ next: ListNode?) { self.val = val; self.next = next; }
 * }
 */

class Solution {
    func reorderList(_ head: ListNode?) {
        guard let head = head else { return }
        
        let mid = middleNode(head)
        
        let secondHalf = reverseList(mid.next)
        mid.next = nil
        
        mergeTwoLists(head, secondHalf)
    }
    
    private func middleNode(_ head: ListNode?) -> ListNode {
        var slow = head
        var fast = head
        while fast != nil && fast?.next != nil {
            slow = slow?.next
            fast = fast?.next?.next
        }
        return slow!
    }
    
    private func reverseList(_ head: ListNode?) -> ListNode? {
        var prev: ListNode? = nil
        var curr = head
        while curr != nil {
            let nextTemp = curr?.next
            curr?.next = prev
            prev = curr
            curr = nextTemp
        }
        return prev
    }
    
    private func mergeTwoLists(_ l1: ListNode?, _ l2: ListNode?) {
        var l1 = l1
        var l2 = l2
        while l1 != nil && l2 != nil {
            let l1Next = l1?.next
            let l2Next = l2?.next
            
            l1?.next = l2
            if l1Next == nil {
                break
            }
            l2?.next = l1Next
            
            l1 = l1Next
            l2 = l2Next
        }
    }
}