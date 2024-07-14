/**
*    public class ListNode {
*        var val: Int
*        var next: ListNode?
*        init(_ x: Int) {
*            self.val = x
*            self.next = nil
*        }
*    }
*/
class Solution {
    func deleteNode(_ node: ListNode?) {
        guard let node = node, let next = node.next else { return }
        node.val = next.val
        node.next = next.next
    }
}