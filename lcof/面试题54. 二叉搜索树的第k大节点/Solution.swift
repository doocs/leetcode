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
    private var k: Int = 0
    private var ans: Int = 0

    func kthLargest(_ root: TreeNode?, _ k: Int) -> Int {
        self.k = k
        dfs(root)
        return ans
    }

    private func dfs(_ root: TreeNode?) {
        guard let root = root, k > 0 else { return }
        dfs(root.right)
        k -= 1
        if k == 0 {
            ans = root.val
            return
        }
        dfs(root.left)
    }
}