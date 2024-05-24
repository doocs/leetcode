/* Definition for a Node.
* public class Node {
*     public var val: Int
*     public var left: Node?
*     public var right: Node?

*     public init() {
*         self.val = 0
*         self.left = nil
*         self.right = nil
*     }

*     public init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }

*     public init(_ val: Int, _ left: Node?, _ right: Node?) {
*         self.val = val
*         self.left = left
*         self.right = right
*     }
* }
*/

class Solution {
    private var head: Node?
    private var pre: Node?

    func treeToDoublyList(_ root: Node?) -> Node? {
        if root == nil {
            return nil
        }
        dfs(root)
        head?.left = pre
        pre?.right = head
        return head
    }

    private func dfs(_ root: Node?) {
        guard let root = root else {
            return
        }
        dfs(root.left)
        if let preNode = pre {
            preNode.right = root
        } else {
            head = root
        }
        root.left = pre
        pre = root
        dfs(root.right)
    }
}