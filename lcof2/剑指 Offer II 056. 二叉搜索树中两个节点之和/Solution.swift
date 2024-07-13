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
    private var nodes: Set<Int> = []

    func findTarget(_ root: TreeNode?, _ k: Int) -> Bool {
        nodes = []
        return find(root, k)
    }

    private func find(_ root: TreeNode?, _ k: Int) -> Bool {
        guard let node = root else {
            return false
        }
        if nodes.contains(k - node.val) {
            return true
        }
        nodes.insert(node.val)
        return find(node.left, k) || find(node.right, k)
    }
}