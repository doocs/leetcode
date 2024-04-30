/* class TreeNode {
*    var val: Int
*    var left: TreeNode?
*    var right: TreeNode?
*    
*    init(_ val: Int, _ left: TreeNode? = nil, _ right: TreeNode? = nil) {
*        self.val = val
*        self.left = left
*        self.right = right
*    }
* } 
*/

class Solution {
    private var cnt: [Int: Int] = [:]
    private var target: Int = 0

    func pathSum(_ root: TreeNode?, _ sum: Int) -> Int {
        cnt[0] = 1
        target = sum
        return dfs(root, 0)
    }

    private func dfs(_ root: TreeNode?, _ s: Int) -> Int {
        guard let root = root else {
            return 0
        }
        let newSum = s + root.val
        let ans = cnt[newSum - target, default: 0]
        
        cnt[newSum, default: 0] += 1
        let leftPaths = dfs(root.left, newSum)
        let rightPaths = dfs(root.right, newSum)
        cnt[newSum, default: 0] -= 1
        
        return ans + leftPaths + rightPaths
    }
}