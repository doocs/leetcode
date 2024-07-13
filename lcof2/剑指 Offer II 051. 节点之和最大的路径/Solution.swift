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
    private var ans = Int.min

    func maxPathSum(_ root: TreeNode?) -> Int {
        _ = dfs(root)
        return ans
    }

    private func dfs(_ root: TreeNode?) -> Int {
        guard let root = root else {
            return 0
        }
        let left = max(0, dfs(root.left))
        let right = max(0, dfs(root.right))
        ans = max(ans, root.val + left + right)
        return root.val + max(left, right)
    }
}