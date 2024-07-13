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
    func sumNumbers(_ root: TreeNode?) -> Int {
        return dfs(root, 0)
    }

    private func dfs(_ root: TreeNode?, _ presum: Int) -> Int {
        guard let root = root else {
            return 0
        }
        let s = presum * 10 + root.val
        if root.left == nil && root.right == nil {
            return s
        }
        return dfs(root.left, s) + dfs(root.right, s)
    }
}