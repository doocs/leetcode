/* public class TreeNode {
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
    func levelOrder(_ root: TreeNode?) -> [Int] {
        guard let root = root else {
            return []
        }
        
        var queue: [TreeNode] = [root]
        var result: [Int] = []
        
        while !queue.isEmpty {
            let node = queue.removeFirst()
            result.append(node.val)
            
            if let left = node.left {
                queue.append(left)
            }
            if let right = node.right {
                queue.append(right)
            }
        }
        
        return result
    }
}