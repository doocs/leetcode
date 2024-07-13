/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     var val: Int
 *     var next: ListNode?
 *     init(_ val: Int, _ next: ListNode? = nil) {
 *         self.val = val
 *         self.next = next
 *     }
 * }
 */

class Solution {
    func deleteNode(_ head: ListNode?, _ val: Int) -> ListNode? {
        let dummy = ListNode(0, head)
        var current: ListNode? = dummy
        
        while current?.next != nil {
            if current?.next?.val == val {
                current?.next = current?.next?.next
                break
            }
            current = current?.next
        }
        
        return dummy.next
    }
}