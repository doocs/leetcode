/**
 * Definition for singly-linked list.
 * public class ListNode {
 *   var val: Int
 *   var next: ListNode?
 *   init(_ x: Int, _ next: ListNode? = nil) {
 *       self.val = x
 *       self.next = next
 *   }
 * }
*/

class Solution {
    func removeDuplicateNodes(_ head: ListNode?) -> ListNode? {
        var vis = Set<Int>()
        let pre = ListNode(0, head)
        var current: ListNode? = pre
        
        while current?.next != nil {
            if vis.insert(current!.next!.val).inserted {
                current = current?.next
            } else {
                current?.next = current?.next?.next
            }
        }
        
        return head
    }
}
