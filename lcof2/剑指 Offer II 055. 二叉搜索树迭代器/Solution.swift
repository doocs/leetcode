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

class BSTIterator {
    private var cur = 0
    private var vals = [Int]()

    init(_ root: TreeNode?) {
        inorder(root)
    }

    func next() -> Int {
        let value = vals[cur]
        cur += 1
        return value
    }

    func hasNext() -> Bool {
        return cur < vals.count
    }

    private func inorder(_ root: TreeNode?) {
        guard let node = root else {
            return
        }
        inorder(node.left)
        vals.append(node.val)
        inorder(node.right)
    }
}