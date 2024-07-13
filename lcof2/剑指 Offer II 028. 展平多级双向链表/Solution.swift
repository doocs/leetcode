/* class Node {
*     var val: Int
*     var prev: Node?
*     var next: Node?
*     var child: Node?

*     init(_ val: Int) {
*         self.val = val
*         self.prev = nil
*         self.next = nil
*         self.child = nil
*     }
* }
*/

class Solution {
    private var dummy = Node(0)
    private var tail: Node?

    func flatten(_ head: Node?) -> Node? {
        guard let head = head else {
            return nil
        }
        tail = dummy
        preOrder(head)
        dummy.next?.prev = nil
        return dummy.next
    }

    private func preOrder(_ node: Node?) {
        guard let node = node else {
            return
        }
        let next = node.next
        let child = node.child
        tail?.next = node
        node.prev = tail
        tail = node
        node.child = nil
        preOrder(child)
        preOrder(next)
    }
}