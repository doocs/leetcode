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
    func isSymmetric(_ root: TreeNode?) -> Bool {
        return dfs(root, root)
    }

    private func dfs(_ a: TreeNode?, _ b: TreeNode?) -> Bool {
        if a == nil && b == nil {
            return true
        }
        if a == nil || b == nil || a!.val != b!.val {
            return false
        }
        return dfs(a!.left, b!.right) && dfs(a!.right, b!.left)
    }
}