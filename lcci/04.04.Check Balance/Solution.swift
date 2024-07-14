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
*  } 
*/

class Solution {
    func isBalanced(_ root: TreeNode?) -> Bool {
        return dfs(root) >= 0
    }

    private func dfs(_ root: TreeNode?) -> Int {
        guard let root = root else {
            return 0
        }

        let leftHeight = dfs(root.left)
        let rightHeight = dfs(root.right)
        if leftHeight < 0 || rightHeight < 0 || abs(leftHeight - rightHeight) > 1 {
            return -1
        }
        return max(leftHeight, rightHeight) + 1
    }
}