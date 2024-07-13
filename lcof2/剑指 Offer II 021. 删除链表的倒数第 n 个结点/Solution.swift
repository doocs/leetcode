/* class ListNode {
*     var val: Int
*     var next: ListNode?
*     init() { self.val = 0; self.next = nil }
*     init(_ val: Int) { self.val = val; self.next = nil }
*     init(_ val: Int, _ next: ListNode?) { self.val = val; self.next = next }
* }
*/

class Solution {
    func removeNthFromEnd(_ head: ListNode?, _ n: Int) -> ListNode? {
        let dummy = ListNode(0, head)
        var fast: ListNode? = dummy
        var slow: ListNode? = dummy
        
        var n = n
        while n > 0 {
            fast = fast?.next
            n -= 1
        }
        
        while fast?.next != nil {
            slow = slow?.next
            fast = fast?.next
        }
        
        slow?.next = slow?.next?.next
        return dummy.next
    }
}