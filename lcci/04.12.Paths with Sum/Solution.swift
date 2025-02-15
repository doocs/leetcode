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
    func pathSum(_ root: TreeNode?, _ sum: Int) -> Int {
        var cnt: [Int: Int] = [0: 1]

        func dfs(_ root: TreeNode?, _ s: Int) -> Int {
            guard let root = root else { return 0 }

            var s = s + root.val
            var ans = cnt[s - sum, default: 0]

            cnt[s, default: 0] += 1
            ans += dfs(root.left, s)
            ans += dfs(root.right, s)
            cnt[s, default: 0] -= 1

            return ans
        }

        return dfs(root, 0)
    }
}
