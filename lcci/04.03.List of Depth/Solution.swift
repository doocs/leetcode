/* class TreeNode {
*    var val: Int
*    var left: TreeNode?
*    var right: TreeNode?
*    
*    init(_ val: Int) {
*        self.val = val
*        self.left = nil
*        self.right = nil
*    }
*  }
*/

/* class ListNode {
*    var val: Int
*    var next: ListNode?
*    
*    init(_ val: Int) {
*        self.val = val
*        self.next = nil
*    }
*  }  
*/

class Solution {
    func listOfDepth(_ tree: TreeNode?) -> [ListNode?] {
        var ans = [ListNode?]()
        guard let tree = tree else { return ans }
        
        var q = [TreeNode]()
        q.append(tree)
        
        while !q.isEmpty {
            let dummy = ListNode(0)
            var cur = dummy
            for _ in 0..<q.count {
                let node = q.removeFirst()
                cur.next = ListNode(node.val)
                cur = cur.next!
                
                if let left = node.left {
                    q.append(left)
                }
                if let right = node.right {
                    q.append(right)
                }
            }
            ans.append(dummy.next)
        }
        
        return ans
    }
}