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
    func BSTSequences(_ root: TreeNode?) -> [[Int]] {
        guard let root = root else { return [[]] }

        var result = [[Int]]()
        let prefix = [root.val]
        let leftSeq = BSTSequences(root.left)
        let rightSeq = BSTSequences(root.right)

        for left in leftSeq {
            for right in rightSeq {
                var weaved = [[Int]]()
                weaveLists(left, right, &weaved, prefix)
                result.append(contentsOf: weaved)
            }
        }
        return result
    }

    private func weaveLists(_ first: [Int], _ second: [Int], _ results: inout [[Int]], _ prefix: [Int]) {
        if first.isEmpty || second.isEmpty {
            var result = prefix
            result.append(contentsOf: first)
            result.append(contentsOf: second)
            results.append(result)
            return
        }

        var prefixWithFirst = prefix
        prefixWithFirst.append(first.first!)
        weaveLists(Array(first.dropFirst()), second, &results, prefixWithFirst)

        var prefixWithSecond = prefix
        prefixWithSecond.append(second.first!)
        weaveLists(first, Array(second.dropFirst()), &results, prefixWithSecond)
    }
}