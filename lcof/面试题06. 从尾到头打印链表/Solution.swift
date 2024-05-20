/* public class ListNode {
*    public var val: Int
*    public var next: ListNode?
*    public init(_ val: Int) {
*        self.val = val
*        self.next = nil
*    }
* }
*/

class Solution {
    func reversePrint(_ head: ListNode?) -> [Int] {
        var stack = [Int]()
        var current = head
        while let node = current {
            stack.append(node.val)
            current = node.next
        }
        
        return stack.reversed()
    }
}