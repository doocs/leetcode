/* class TreeNode {
*    var val: Int
*    var left: TreeNode?
*    var right: TreeNode?
*    init(_ val: Int) {
*        self.val = val
*        self.left = nil
*        self.right = nil
*    }
* }
*/

class Solution {
    private var k: Int = 0
    
    func maxValue(_ root: TreeNode?, _ k: Int) -> Int {
        self.k = k
        return dfs(root).max() ?? 0
    }
    
    private func dfs(_ root: TreeNode?) -> [Int] {
        var ans = [Int](repeating: 0, count: k + 1)
        guard let root = root else {
            return ans
        }
        let l = dfs(root.left)
        let r = dfs(root.right)
        
        ans[0] = (l.max() ?? 0) + (r.max() ?? 0)
        
        for i in 0..<k {
            for j in 0..<k - i {
                ans[i + j + 1] = max(ans[i + j + 1], root.val + l[i] + r[j])
            }
        }
        return ans
    }
}