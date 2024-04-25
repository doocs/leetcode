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
    private var prev: TreeNode?

    func isValidBST(_ root: TreeNode?) -> Bool {
        return dfs(root)
    }

    private func dfs(_ root: TreeNode?) -> Bool {
        guard let root = root else {
            return true
        }

        if !dfs(root.left) {
            return false
        }
    
        if let prev = prev, prev.val >= root.val {
            return false
        }
    
        prev = root

        return dfs(root.right)
    }
}
