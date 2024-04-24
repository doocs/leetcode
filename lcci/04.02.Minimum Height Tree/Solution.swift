/**
* class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
*    
*     init(_ val: Int, _ left: TreeNode? = nil, _ right: TreeNode? = nil) {
*         self.val = val
*         self.left = left
*         self.right = right
*     }
* }
*/

class Solution {
    private var nums: [Int]!

    func sortedArrayToBST(_ nums: [Int]) -> TreeNode? {
        self.nums = nums
        return dfs(0, nums.count - 1)
    }

    private func dfs(_ l: Int, _ r: Int) -> TreeNode? {
        if l > r {
            return nil
        }
        let mid = (l + r) / 2
        return TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r))
    }
}
