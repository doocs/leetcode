/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     var val: Int
 *     var next: ListNode?
 *     init(_ x: Int, _ next: ListNode? = nil) {
 *         self.val = x
 *         self.next = next
 *     }
 * }
 */

class Solution {
    func kthToLast(_ head: ListNode?, _ k: Int) -> Int {
        var slow = head
        var fast = head
        var k = k
        
        while k > 0 {
            fast = fast?.next
            k -= 1
        }
        
        while fast != nil {
            slow = slow?.next
            fast = fast?.next
        }
        
        return slow?.val ?? 0
    }
}