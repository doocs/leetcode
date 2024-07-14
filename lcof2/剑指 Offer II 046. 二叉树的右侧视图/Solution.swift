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
    func rightSideView(_ root: TreeNode?) -> [Int] {
        var ans = [Int]()
        guard let root = root else {
            return ans
        }
        var q = [TreeNode]()
        q.append(root)
        while !q.isEmpty {
            ans.append(q[0].val)
            for _ in 0..<q.count {
                let node = q.removeFirst()
                if let right = node.right {
                    q.append(right)
                }
                if let left = node.left {
                    q.append(left)
                }
            }
        }
        return ans
    }
}