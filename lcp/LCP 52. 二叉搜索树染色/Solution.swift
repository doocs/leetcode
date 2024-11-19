/* public class TreeNode {
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
    private var treeValues: [Int] = []

    func getNumber(_ root: TreeNode?, _ ops: [[Int]]) -> Int {
        collectValues(root)

        treeValues.sort()

        var ans = 0
        for op in ops.reversed() {
            let t = op[0]
            let x = op[1]
            let y = op[2]
            var indicesToRemove: [Int] = []

            for i in 0..<treeValues.count {
                let val = treeValues[i]
                if val >= x && val <= y {
                    indicesToRemove.append(i)
                    ans += t
                }
            }

            for index in indicesToRemove.reversed() {
                treeValues.remove(at: index)
            }
        }

        return ans
    }

    private func collectValues(_ root: TreeNode?) {
        guard let root = root else { return }
        treeValues.append(root.val)
        collectValues(root.left)
        collectValues(root.right)
    }
}