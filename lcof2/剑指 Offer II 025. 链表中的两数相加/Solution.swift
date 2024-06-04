/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     var val: Int
 *     var next: ListNode?
 *     init() { self.val = 0; self.next = nil; }
 *     init(_ val: Int) { self.val = val; self.next = nil; }
 *     init(_ val: Int, _ next: ListNode?) { self.val = val; self.next = next; }
 * }
 */
 
class Solution {
    func addTwoNumbers(_ l1: ListNode?, _ l2: ListNode?) -> ListNode? {
        var s1: [Int] = []
        var s2: [Int] = []
        
        var node1 = l1
        var node2 = l2
        
        while let n1 = node1 {
            s1.append(n1.val)
            node1 = n1.next
        }
        
        while let n2 = node2 {
            s2.append(n2.val)
            node2 = n2.next
        }
        
        var carry = 0
        let dummy: ListNode? = ListNode(0)
        
        while !s1.isEmpty || !s2.isEmpty || carry != 0 {
            carry += (s1.isEmpty ? 0 : s1.removeLast()) + (s2.isEmpty ? 0 : s2.removeLast())
            let node = ListNode(carry % 10)
            node.next = dummy?.next
            dummy?.next = node
            carry /= 10
        }
        
        return dummy?.next
    }
}