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
    func pruneTree(_ root: TreeNode?) -> TreeNode? {
        guard let root = root else {
            return nil
        }
        root.left = pruneTree(root.left)
        root.right = pruneTree(root.right)
        if root.val == 0 && root.left == nil && root.right == nil {
            return nil
        }
        return root
    }
}