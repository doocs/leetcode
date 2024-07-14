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
    func isSubStructure(_ A: TreeNode?, _ B: TreeNode?) -> Bool {
        guard let A = A, let B = B else {
            return false
        }
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B)
    }

    private func dfs(_ A: TreeNode?, _ B: TreeNode?) -> Bool {
        if B == nil {
            return true
        }
        guard let A = A else {
            return false
        }
        if A.val != B!.val {
            return false
        }
        return dfs(A.left, B?.left) && dfs(A.right, B?.right)
    }
}