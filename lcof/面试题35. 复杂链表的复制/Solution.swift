/* public class Node: Hashable {
*     public var val: Int
*     public var next: Node?
*     public var random: Node?
    
*     public init(_ val: Int) {
*         self.val = val
*         self.next = nil
*         self.random = nil
*     }
    
*     public static func == (lhs: Node, rhs: Node) -> Bool {
*         return lhs === rhs
*     }
    
*     public func hash(into hasher: inout Hasher) {
*         hasher.combine(ObjectIdentifier(self))
*     }
* }
*/

class Solution {
    func copyRandomList(_ head: Node?) -> Node? {
        var d = [Node: Node]()
        let dummy = Node(0)
        var tail: Node? = dummy
        var cur = head
        
        while cur != nil {
            tail?.next = Node(cur!.val)
            tail = tail?.next
            d[cur!] = tail
            cur = cur?.next
        }
        
        tail = dummy.next
        cur = head
        
        while cur != nil {
            tail?.random = d[cur!.random ?? Node(0)]
            tail = tail?.next
            cur = cur?.next
        }
        
        return dummy.next
    }
}