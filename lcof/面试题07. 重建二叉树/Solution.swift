/* public class TreeNode {
*     public var val: Int
*     public var left: TreeNode?
*     public var right: TreeNode?
*     public init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
*  }
*/

class Solution {
    private var d = [Int: Int]()
    private var preorder: [Int] = []
    private var inorder: [Int] = []

    func buildTree(_ preorder: [Int], _ inorder: [Int]) -> TreeNode? {
        let n = inorder.count
        for i in 0..<n {
            d[inorder[i]] = i
        }
        self.preorder = preorder
        self.inorder = inorder
        return dfs(0, 0, n)
    }

    private func dfs(_ i: Int, _ j: Int, _ n: Int) -> TreeNode? {
        if n < 1 {
            return nil
        }
        let k = d[preorder[i]]!
        let l = k - j
        let root = TreeNode(preorder[i])
        root.left = dfs(i + 1, j, l)
        root.right = dfs(i + 1 + l, k + 1, n - l - 1)
        return root
    }
}