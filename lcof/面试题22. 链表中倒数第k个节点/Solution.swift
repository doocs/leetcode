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
    func getKthFromEnd(_ head: ListNode?, _ k: Int) -> ListNode? {
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
        
        return slow
    }
}