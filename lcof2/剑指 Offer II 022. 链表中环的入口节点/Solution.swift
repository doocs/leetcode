/* class ListNode {
*     var val: Int
*     var next: ListNode?
*     init(_ val: Int) {
*         self.val = val
*         self.next = nil
*     }
* }
*/

class Solution {
    func detectCycle(_ head: ListNode?) -> ListNode? {
        var fast = head
        var slow = head
        
        while fast != nil && fast?.next != nil {
            slow = slow?.next
            fast = fast?.next?.next
            
            if slow === fast {
                var ans = head
                while ans !== slow {
                    ans = ans?.next
                    slow = slow?.next
                }
                return ans
            }
        }
        return nil
    }
}