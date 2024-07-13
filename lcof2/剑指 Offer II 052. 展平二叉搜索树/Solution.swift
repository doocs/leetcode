/* class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
*     init() {
*         self.val = 0
*         self.left = nil
*         self.right = nil
*     }
*     init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
*     init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
*         self.val = val
*         self.left = left
*         self.right = right
*     }
* }
*/

class Solution {
    func increasingBST(_ root: TreeNode?) -> TreeNode? {
        var head: TreeNode? = nil
        var tail: TreeNode? = nil
        var stack = [TreeNode]()
        var cur = root
        
        while !stack.isEmpty || cur != nil {
            while cur != nil {
                stack.append(cur!)
                cur = cur?.left
            }
            cur = stack.removeLast()
            if head == nil {
                head = cur
            } else {
                tail?.right = cur
            }
            tail = cur
            cur?.left = nil
            cur = cur?.right
        }
        return head
    }
}