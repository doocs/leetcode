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
    func checkSubTree(_ t1: TreeNode?, _ t2: TreeNode?) -> Bool {
        if t2 == nil {
            return true
        }
        if t1 == nil {
            return false
        }
        if isSameTree(t1, t2) {
            return true
        }
        return checkSubTree(t1!.left, t2) || checkSubTree(t1!.right, t2)
    }

    private func isSameTree(_ t1: TreeNode?, _ t2: TreeNode?) -> Bool {
        if t1 == nil && t2 == nil {
            return true
        }
        if t1 == nil || t2 == nil {
            return false
        }
        if t1!.val != t2!.val {
            return false
        }
        return isSameTree(t1!.left, t2!.left) && isSameTree(t1!.right, t2!.right)
    }
}