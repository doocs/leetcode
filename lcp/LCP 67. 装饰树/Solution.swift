/* class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
    
*     init() { self.val = 0; self.left = nil; self.right = nil }
*     init(_ val: Int) { self.val = val; self.left = nil; self.right = nil }
*     init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
*         self.val = val
*         self.left = left
*         self.right = right
*     }
* }
*/

class Solution {
    func expandBinaryTree(_ root: TreeNode?) -> TreeNode? {
        return dfs(root)
    }
    
    private func dfs(_ root: TreeNode?) -> TreeNode? {
        guard let root = root else { return nil }
        
        let leftChild = dfs(root.left)
        let rightChild = dfs(root.right)
        
        if let leftChild = leftChild {
            root.left = TreeNode(-1, leftChild, nil)
        }
        if let rightChild = rightChild {
            root.right = TreeNode(-1, nil, rightChild)
        }
        
        return root
    }
}
