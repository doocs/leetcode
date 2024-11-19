/* class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
*     init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
* }
*/

class Solution {
    private var uniqueColors: Set<Int> = []
    
    func numColor(_ root: TreeNode?) -> Int {
        dfs(root)
        return uniqueColors.count
    }
    
    private func dfs(_ node: TreeNode?) {
        guard let node = node else { return }
        uniqueColors.insert(node.val)
        dfs(node.left)
        dfs(node.right)
    }
}