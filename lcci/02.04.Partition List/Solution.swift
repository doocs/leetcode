/** public class ListNode {
*    var val: Int
*    var next: ListNode?
*    init(_ x: Int) {
*        self.val = x
*        self.next = nil
*    }
* }
*/

class Solution {
    func partition(_ head: ListNode?, _ x: Int) -> ListNode? {
        let leftDummy = ListNode(0)
        let rightDummy = ListNode(0)
        var left = leftDummy
        var right = rightDummy
        var head = head
        
        while let current = head {
            if current.val < x {
                left.next = current
                left = left.next!
            } else {
                right.next = current
                right = right.next!
            }
            head = head?.next
        }
        
        right.next = nil
        left.next = rightDummy.next
        
        return leftDummy.next
    }
}