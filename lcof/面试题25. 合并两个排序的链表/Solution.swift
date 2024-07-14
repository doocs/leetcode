/* public class ListNode {
*     var val: Int
*     var next: ListNode?
*     init(_ val: Int) {
*         self.val = val
*         self.next = nil
*     }
* }
*/

class Solution {
    func mergeTwoLists(_ l1: ListNode?, _ l2: ListNode?) -> ListNode? {
        let dummy = ListNode(0)
        var cur: ListNode? = dummy
        var l1 = l1
        var l2 = l2
        
        while let l1Node = l1, let l2Node = l2 {
            if l1Node.val <= l2Node.val {
                cur?.next = l1Node
                l1 = l1Node.next
            } else {
                cur?.next = l2Node
                l2 = l2Node.next
            }
            cur = cur?.next
        }
        
        cur?.next = l1 ?? l2
        
        return dummy.next
    }
}