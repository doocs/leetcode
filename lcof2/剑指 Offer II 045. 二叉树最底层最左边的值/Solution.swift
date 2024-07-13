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
    func findBottomLeftValue(_ root: TreeNode?) -> Int {
        var q = [TreeNode]()
        q.append(root!)
        var ans = -1
        while !q.isEmpty {
            let n = q.count
            for i in 0..<n {
                let node = q.removeFirst()
                if i == 0 {
                    ans = node.val
                }
                if let left = node.left {
                    q.append(left)
                }
                if let right = node.right {
                    q.append(right)
                }
            }
        }
        return ans
    }
}