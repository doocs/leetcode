/**
 * Definition for a binary tree node.
 * public class TreeNode {
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
    func lowestCommonAncestor(_ root: TreeNode?, _ p: TreeNode?, _ q: TreeNode?) -> TreeNode? {
        guard let p = p, let q = q else {
            return nil
        }

        var node = root
        while let current = node {
            if current.val < p.val && current.val < q.val {
                node = current.right
            } else if current.val > p.val && current.val > q.val {
                node = current.left
            } else {
                return current
            }
        }
        return nil
    }
}
