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
    private var s = 0

    func convertBST(_ root: TreeNode?) -> TreeNode? {
        dfs(root)
        return root
    }

    private func dfs(_ root: TreeNode?) {
        guard let node = root else {
            return
        }
        dfs(node.right)
        s += node.val
        node.val = s
        dfs(node.left)
    }
}