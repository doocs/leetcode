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
    func inorderSuccessor(_ root: TreeNode?, _ p: TreeNode?) -> TreeNode? {
        var root = root
        var ans: TreeNode? = nil
        while root != nil {
            if root!.val > p!.val {
                ans = root
                root = root!.left
            } else {
                root = root!.right
            }
        }
        return ans
    }
}