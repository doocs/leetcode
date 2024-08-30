/** class ListNode {
*    var val: Int
*    var next: ListNode?
*    init() { self.val = 0; self.next = nil }
*    init(_ val: Int) { self.val = val; self.next = nil }
*    init(_ val: Int, _ next: ListNode?) { self.val = val; self.next = next }
* }
*/

class Solution {
    func mergeKLists(_ lists: [ListNode?]) -> ListNode? {
        let n = lists.count
        if n == 0 {
            return nil
        }
        
        var mergedList: ListNode? = lists[0]
        for i in 1..<n {
            mergedList = mergeLists(mergedList, lists[i])
        }
        return mergedList
    }
    
    private func mergeLists(_ l1: ListNode?, _ l2: ListNode?) -> ListNode? {
        let dummy = ListNode()
        var cur = dummy
        var l1 = l1
        var l2 = l2
        
        while let node1 = l1, let node2 = l2 {
            if node1.val <= node2.val {
                cur.next = node1
                l1 = node1.next
            } else {
                cur.next = node2
                l2 = node2.next
            }
            cur = cur.next!
        }
        cur.next = l1 ?? l2
        return dummy.next
    }
}