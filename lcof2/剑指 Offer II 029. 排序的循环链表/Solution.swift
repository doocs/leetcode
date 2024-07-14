/* class Node {
*     var val: Int
*     var next: Node?

*     init(_ val: Int) {
*         self.val = val
*         self.next = nil
*     }
* }
*/

class Solution {
    func insert(_ head: Node?, _ insertVal: Int) -> Node? {
        let newNode = Node(insertVal)
        if head == nil {
            newNode.next = newNode
            return newNode
        }
        
        var current = head
        repeat {
            if current!.val <= insertVal && insertVal <= current!.next!.val {
                break
            }

            if current!.val > current!.next!.val && (insertVal >= current!.val || insertVal <= current!.next!.val) {
                break
            }

            if current!.next === head {
                break
            }
            current = current!.next
        } while current !== head

        newNode.next = current!.next
        current!.next = newNode
        return head
    }
}
