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
    func closeLampInTree(_ root: TreeNode?) -> Int {
        return dfs(root)[0]
    }

    private func dfs(_ root: TreeNode?) -> [Int] {
        var ans = [Int](repeating: 0, count: 4)
        guard let root = root else {
            return ans
        }

        let left = dfs(root.left)
        let right = dfs(root.right)

        let l1 = left[0], l2 = left[1], l3 = left[2], l4 = left[3]
        let r1 = right[0], r2 = right[1], r3 = right[2], r4 = right[3]

        if root.val != 0 {
            ans[0] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3)
            ans[1] = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2)
            ans[2] = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2)
            ans[3] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1)
        } else {
            ans[0] = min(l1 + r1, l2 + r2 + 2, l3 + r3 + 2, l4 + r4 + 2)
            ans[1] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 3, l4 + r4 + 1)
            ans[2] = min(l1 + r1 + 1, l2 + r2 + 1, l3 + r3 + 1, l4 + r4 + 3)
            ans[3] = min(l1 + r1 + 2, l2 + r2, l3 + r3 + 2, l4 + r4 + 2)
        }

        return ans
    }

    private func min(_ nums: Int...) -> Int {
        return nums.min() ?? Int.max
    }
}