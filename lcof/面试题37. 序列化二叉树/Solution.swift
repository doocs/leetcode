
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

public class Codec {
    func serialize(_ root: TreeNode?) -> String {
        guard let root = root else {
            return "null"
        }
        var result = [String]()
        var queue = [TreeNode?]()
        queue.append(root)
        while !queue.isEmpty {
            let node = queue.removeFirst()
            if let node = node {
                result.append("\(node.val)")
                queue.append(node.left)
                queue.append(node.right)
            } else {
                result.append("#")
            }
        }
        return result.joined(separator: ",")
    }

    func deserialize(_ data: String) -> TreeNode? {
        if data == "null" {
            return nil
        }
        let vals = data.split(separator: ",").map { String($0) }
        var i = 0
        let root = TreeNode(Int(vals[i])!)
        var queue = [TreeNode]()
        queue.append(root)
        i += 1
        while !queue.isEmpty {
            let node = queue.removeFirst()
            if vals[i] != "#" {
                node.left = TreeNode(Int(vals[i])!)
                queue.append(node.left!)
            }
            i += 1
            if vals[i] != "#" {
                node.right = TreeNode(Int(vals[i])!)
                queue.append(node.right!)
            }
            i += 1
        }
        return root
    }
}