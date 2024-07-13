/* public class TreeNode {
*     public var val: Int
*     public var left: TreeNode?
*     public var right: TreeNode?
*     public init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
* }
*/

class Solution {
    func isBalanced(_ root: TreeNode?) -> Bool {
        return dfs(root) != -1
    }

    private func dfs(_ root: TreeNode?) -> Int {
        guard let root = root else {
            return 0
        }
        let leftDepth = dfs(root.left)
        let rightDepth = dfs(root.right)
        if leftDepth == -1 || rightDepth == -1 || abs(leftDepth - rightDepth) > 1 {
            return -1
        }
        return 1 + max(leftDepth, rightDepth)
    }
}