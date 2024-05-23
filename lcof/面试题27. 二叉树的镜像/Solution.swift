/* public class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
*     init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
* }
*/

class Solution {
    func mirrorTree(_ root: TreeNode?) -> TreeNode? {
        guard let root = root else {
            return nil
        }
        let temp = root.left
        root.left = root.right
        root.right = temp
        _ = mirrorTree(root.left)
        _ = mirrorTree(root.right)
        return root
    }
}