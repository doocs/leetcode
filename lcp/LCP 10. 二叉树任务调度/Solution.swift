/**
* public class TreeNode {
*     public var val: Int
*     public var left: TreeNode?
*     public var right: TreeNode?
*     public init() { self.val = 0; self.left = nil; self.right = nil; }
*     public init(_ val: Int) { self.val = val; self.left = nil; self.right = nil; }
*     public init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
*         self.val = val
*         self.left = left
*         self.right = right
*     }
* }
*/

class Solution {
    func minimalExecTime(_ root: TreeNode?) -> Double {
        return dfs(root)[1]
    }
    
    private func dfs(_ root: TreeNode?) -> [Double] {
        guard let root = root else { return [0.0, 0.0] }
        
        let left = dfs(root.left)
        let right = dfs(root.right)
        
        let sum = left[0] + right[0] + Double(root.val)
        let time = max(max(left[1], right[1]), (left[0] + right[0]) / 2) + Double(root.val)
        
        return [sum, time]
    }
}