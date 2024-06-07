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
    private var cnt = [Int: Int]()
    private var targetSum: Int = 0

    func pathSum(_ root: TreeNode?, _ targetSum: Int) -> Int {
        self.targetSum = targetSum
        cnt[0] = 1
        return dfs(root, 0)
    }

    private func dfs(_ node: TreeNode?, _ s: Int) -> Int {
        guard let node = node else {
            return 0
        }
        var s = s
        s += node.val
        var ans = cnt[s - targetSum, default: 0]
        cnt[s, default: 0] += 1
        ans += dfs(node.left, s)
        ans += dfs(node.right, s)
        cnt[s]! -= 1
        return ans
    }
}