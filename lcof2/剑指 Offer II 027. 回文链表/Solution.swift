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
    func isPalindrome(_ head: ListNode?) -> Bool {
        guard let head = head else { return true }
        
        var slow = head
        var fast = head.next
        while fast != nil && fast?.next != nil {
            slow = slow.next!
            fast = fast?.next?.next
        }
        
        var cur = slow.next
        slow.next = nil
        var prev: ListNode? = nil
        while cur != nil {
            let nextTemp = cur?.next
            cur?.next = prev
            prev = cur
            cur = nextTemp
        }
        
        var left = head
        var right = prev
        while right != nil {
            if left.val != right?.val {
                return false
            }
            left = left.next!
            right = right?.next
        }
        return true
    }
}