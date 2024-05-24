/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     public var val: Int
 *     public var left: TreeNode?
 *     public var right: TreeNode?
 *     public init() { self.val = 0; self.left = nil; self.right = nil; }
 *     public init(_ val: Int) { self.val = val; self.left = nil; self.right = nil; }
 *     public init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
 *         self.val = val
 *         self.left = left
 *         self.right = right
 *     }
 * }
 */
 
class Solution {
    private var t = [Int]()
    private var ans = [[Int]]()

    func pathSum(_ root: TreeNode?, _ target: Int) -> [[Int]] {
        dfs(root, target)
        return ans
    }

    private func dfs(_ root: TreeNode?, _ s: Int) {
        guard let root = root else {
            return
        }
        t.append(root.val)
        let remainingSum = s - root.val
        if root.left == nil && root.right == nil && remainingSum == 0 {
            ans.append(Array(t))
        }
        dfs(root.left, remainingSum)
        dfs(root.right, remainingSum)
        t.removeLast()
    }
}