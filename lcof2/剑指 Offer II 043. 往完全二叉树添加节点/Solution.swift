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

class CBTInserter {
    private var tree: [TreeNode] = []

    init(_ root: TreeNode?) {
        guard let root = root else { return }
        var q: [TreeNode] = [root]
        while !q.isEmpty {
            for _ in 0..<q.count {
                let node = q.removeFirst()
                tree.append(node)
                if let left = node.left {
                    q.append(left)
                }
                if let right = node.right {
                    q.append(right)
                }
            }
        }
    }

    func insert(_ val: Int) -> Int {
        let p = tree[(tree.count - 1) / 2]
        let node = TreeNode(val)
        tree.append(node)
        if p.left == nil {
            p.left = node
        } else {
            p.right = node
        }
        return p.val
    }

    func get_root() -> TreeNode? {
        return tree.isEmpty ? nil : tree[0]
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * let obj = CBTInserter(root)
 * let param_1 = obj.insert(val)
 * let param_2 = obj.get_root()
 */