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
    func reverseList(_ head: ListNode?) -> ListNode? {
        let dummy = ListNode(0)
        var curr = head
        
        while curr != nil {
            let next = curr?.next
            curr?.next = dummy.next
            dummy.next = curr
            curr = next
        }
        
        return dummy.next
    }
}