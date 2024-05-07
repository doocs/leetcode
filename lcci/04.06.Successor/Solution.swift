/* class TreeNode {
*    var val: Int
*    var left: TreeNode?
*    var right: TreeNode?
*
*    init(_ val: Int) {
*        self.val = val
*        self.left = nil
*        self.right = nil
*    }
* } 
*/

class Solution {
    func inorderSuccessor(_ root: TreeNode?, _ p: TreeNode?) -> TreeNode? {
        var current = root
        var successor: TreeNode? = nil
        
        while let node = current {
            if node.val > p!.val {
                successor = node
                current = node.left
            } else {
                current = node.right
            }
        }
        return successor
    }
}