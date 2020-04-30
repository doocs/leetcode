/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} val
 * @return {ListNode}
 */
var deleteNode = function(head, val) {
    let node = head
    if(node.val === val) {
        node = node.next
        head = node
    } else {
        while(node.next) {
            if(node.next.val === val) {
                node.next = node.next.next
                break
            }
            node = node.next
        }
    }
    return head
};