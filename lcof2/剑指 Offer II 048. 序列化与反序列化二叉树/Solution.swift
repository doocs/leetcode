/* class TreeNode {
*     var val: Int
*     var left: TreeNode?
*     var right: TreeNode?
*     init() {
*         self.val = 0
*         self.left = nil
*         self.right = nil
*     }
*     init(_ val: Int) {
*         self.val = val
*         self.left = nil
*         self.right = nil
*     }
*     init(_ val: Int, _ left: TreeNode?, _ right: TreeNode?) {
*         self.val = val
*         self.left = left
*         self.right = right
*     }
* }
*/

class Codec {
    private let NULL = "#"
    private let SEP = ","

    func serialize(_ root: TreeNode?) -> String {
        guard let root = root else { return "" }
        var sb = ""
        preorder(root, &sb)
        return sb
    }

    private func preorder(_ root: TreeNode?, _ sb: inout String) {
        guard let root = root else {
            sb += NULL + SEP
            return
        }
        sb += "\(root.val)" + SEP
        preorder(root.left, &sb)
        preorder(root.right, &sb)
    }

    func deserialize(_ data: String) -> TreeNode? {
        guard !data.isEmpty else { return nil }
        var vals = data.split(separator: Character(SEP)).map { String($0) }
        return deserialize(&vals)
    }

    private func deserialize(_ vals: inout [String]) -> TreeNode? {
        if vals.isEmpty { return nil }
        let first = vals.removeFirst()
        if first == NULL {
            return nil
        }
        let root = TreeNode(Int(first)!)
        root.left = deserialize(&vals)
        root.right = deserialize(&vals)
        return root
    }
}
/**
 * Your functions will be called as such:
 * let codec = Codec()
 * let serializedDatacodec.serialize(root);
 * codec.deserialize(serializedData);
 */